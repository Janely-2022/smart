package com.ega.smartoutlet.markers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.ega.smartoutlet.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.Locale;
import java.util.Map;

@SuppressLint("ViewConstructor")
public class CustomMarkerView extends MarkerView {

    private final TextView tvDay;
    private final TextView tvPower;
    private final Map<Integer, String> dayToDateMap;

    public CustomMarkerView(Context context, int layoutResource, Map<Integer, String> dayToDateMap) {
        super(context, layoutResource);
        this.dayToDateMap = dayToDateMap;
        tvDay = findViewById(R.id.tvDay);
        tvPower = findViewById(R.id.tvPower);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        int dayIndex = (int) e.getX();

        String formattedDate = dayToDateMap.get(dayIndex);

        String dayOfWeek = getDayOfWeek(dayIndex);
        tvDay.setText(String.format(Locale.getDefault(), "Date: %s\nDay: %s", formattedDate, dayOfWeek));
        tvPower.setText(String.format(Locale.getDefault(), "Total Power: %.2f W", e.getY()));

        super.refreshContent(e, highlight);
    }

    private String getDayOfWeek(int index) {
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        return daysOfWeek[index % 7];
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-((float) getWidth() / 2), -getHeight());
    }
}
