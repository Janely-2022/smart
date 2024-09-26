package com.ega.smartoutlet;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.adapters.SensorReadingsAdapter;
import com.ega.smartoutlet.httpRequests.ApiClient;
import com.ega.smartoutlet.httpRequests.ApiService;
import com.ega.smartoutlet.httpRequests.ResponseHandler;
import com.ega.smartoutlet.markers.CustomMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ega.smartoutlet.models.SensorReadings;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphsFragment extends Fragment {
    private LineChart lineChart;
    private Description description = new Description();
    private ApiService apiService;
    private RecyclerView recyclerView;
    private SensorReadingsAdapter sensorReadingsAdapter;
    private ApiClient apiClient = new ApiClient();
    private Handler handler = new Handler();
    private static final int REFRESH_INTERVAL = 5000;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            fetchSensorData();
            handler.postDelayed(this, REFRESH_INTERVAL);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = layoutInflater.inflate(R.layout.fragment_statistics, container, false);
        lineChart = view.findViewById(R.id.chart);

        recyclerView = view.findViewById(R.id.sensor_readings_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = ApiClient.getStatisticsData(getContext()).create(ApiService.class);
        fetchSensorData();

        return view;
    }

    private void fetchSensorData() {
        Call<ResponseHandler.SensorReadingsResponse> call = apiService.getAllDeviceReadings();

        call.enqueue(new Callback<ResponseHandler.SensorReadingsResponse>() {
            @Override
            public void onResponse(Call<ResponseHandler.SensorReadingsResponse> call, Response<ResponseHandler.SensorReadingsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SensorReadings> readings = response.body().getContent();

                    readings.sort((r1, r2) -> Double.compare(r2.getEnergyConsumed(), r1.getEnergyConsumed()));
                    List<SensorReadings> topReadings = readings.size() > 3 ? readings.subList(0, 3) : readings;

                    Log.d("TAG", "onResponse:  readings   "+ readings.toArray().length);
                    sensorReadingsAdapter = new SensorReadingsAdapter(topReadings);
                    recyclerView.setAdapter(sensorReadingsAdapter);

                    double totalEnergyConsumed = 0;
                    double totalCurrent = 0;
                    int readingsCount = readings.size();

                    for (SensorReadings reading : readings) {
                        totalEnergyConsumed += reading.getEnergyConsumed();
                        totalCurrent += reading.getCurrent();
                    }
                    double averageCurrent = readingsCount > 0 ? totalCurrent / readingsCount : 0;
                    updateCurrentMonthLayout(totalEnergyConsumed, averageCurrent);

                    setLineChartData(readings);
                    Log.d("API_RESPONSE", "Data Received: " + readings);
                } else {
                    Log.e("API_RESPONSE", "No data or unsuccessful request. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseHandler.SensorReadingsResponse> call, Throwable t) {
                Log.e("API_RESPONSE", "Failed to reach API: " + t.getMessage());
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void updateCurrentMonthLayout(double totalEnergyConsumed, double averageCurrent) {
        TextView totalCurrentThisMonthTextView = requireView().findViewById(R.id.total_current_this_month);
        totalCurrentThisMonthTextView.setText(String.format("%.2f A", averageCurrent));
    }

    private void setLineChartData(List<SensorReadings> readings) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        SimpleDateFormat sdfDay = new SimpleDateFormat("E", Locale.getDefault());
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        description.setText("Weekly Electric Status");
        description.setTextSize(10);

        Map<Integer, Float> dailyPowerUsage = new HashMap<>();
        Map<Integer, String> dailyDateMapping = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        // Get the current day of the week (e.g., Sunday, Monday...)
        for (SensorReadings reading : readings) {
            Date date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = reading.getCreatedAtDate();
            }
            if (date != null) {
                calendar.setTime(date);
                int dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                dailyPowerUsage.put(dayOfWeekIndex, dailyPowerUsage.getOrDefault(dayOfWeekIndex, 0f) + (float) reading.getPower());
                dailyDateMapping.put(dayOfWeekIndex, sdfDate.format(date));
            }
        }

        for (int i = 0; i < 7; i++) {
            if (!dailyDateMapping.containsKey(i)) {
                calendar.setTime(new Date());
                calendar.set(Calendar.DAY_OF_WEEK, i + 1);
                dailyDateMapping.put(i, sdfDate.format(calendar.getTime()));
            }
        }

        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            float power = dailyPowerUsage.getOrDefault(i, 0f);
            lineEntries.add(new Entry(i, power));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Energy Consumed");
        if (android.os.Build.VERSION.SDK_INT >= 18) {
            Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.blue_gradient);
            lineDataSet.setFillDrawable(drawable);
        } else {
            lineDataSet.setFillColor(Color.BLUE);
        }

        lineDataSet.setDrawFilled(true);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setDescription(description);

        lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return daysOfWeek[(int) value];
            }
        });

        CustomMarkerView markerView = new CustomMarkerView(getContext(), R.layout.custom_marker_view, dailyDateMapping);
        markerView.setChartView(lineChart);
        lineChart.setMarker(markerView);

        lineChart.getXAxis().setGranularity(1f);
        lineChart.getXAxis().setGranularityEnabled(true);

        lineChart.invalidate();
    }

}
