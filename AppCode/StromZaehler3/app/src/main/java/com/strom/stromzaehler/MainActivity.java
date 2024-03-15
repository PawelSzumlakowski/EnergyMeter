package com.strom.stromzaehler;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("1", 2054));
        data.add(new ValueDataEntry("2", 949));
        data.add(new ValueDataEntry("3", 1061));
        data.add(new ValueDataEntry("4", 1143));
        data.add(new ValueDataEntry("5", 1280));
        data.add(new ValueDataEntry("6", 1476));
        data.add(new ValueDataEntry("7", 1706));
        data.add(new ValueDataEntry("8", 2131));
        data.add(new ValueDataEntry("9", 2498));
        data.add(new ValueDataEntry("10", 1498));
        data.add(new ValueDataEntry("11", 1250));
        data.add(new ValueDataEntry("12", 1180));
        data.add(new ValueDataEntry("13", 1880));
        data.add(new ValueDataEntry("14", 1680));
        data.add(new ValueDataEntry("15", 1061));
        data.add(new ValueDataEntry("16", 1143));
        data.add(new ValueDataEntry("17", 1280));
        data.add(new ValueDataEntry("18", 1476));
        data.add(new ValueDataEntry("19", 1706));
        data.add(new ValueDataEntry("20", 2054));
        data.add(new ValueDataEntry("21", 949));
        data.add(new ValueDataEntry("22", 1061));
        data.add(new ValueDataEntry("23", 1143));
        data.add(new ValueDataEntry("24", 1280));
        data.add(new ValueDataEntry("25", 1476));
        data.add(new ValueDataEntry("26", 1706));
        data.add(new ValueDataEntry("27", 2131));
        data.add(new ValueDataEntry("28", 2498));
        data.add(new ValueDataEntry("29", 1498));
        data.add(new ValueDataEntry("30", 1250));

        Column column = ((Cartesian) cartesian).column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Strom Verbrauch");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Zeit");
        cartesian.yAxis(0).title("kWh");

        anyChartView.setChart(cartesian);
    }
}