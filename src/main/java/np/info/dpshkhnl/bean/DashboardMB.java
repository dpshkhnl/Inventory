/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.info.dpshkhnl.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Dpshkhnl
 */
@Named
@ViewScoped
public class DashboardMB implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private ArrayList<String> x = new ArrayList<String>();
    private DashboardModel dashboardModel ;

    @PostConstruct
    public void init() {
       
        x.add("Jan");
        x.add("Feb");
        x.add("March");
        x.add("April");
        x.add("May");
        x.add("June");
        x.add("July");
        x.add("Aug");
        x.add("Sept");
        x.add("Oct");
        x.add("Nov");
        x.add("Dec");
         createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {

        animatedModel1 = initLinearModel();
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
       // Axis xAxis = animatedModel1.getAxis(AxisType.X);
        yAxis.setMax(10);
//        xAxis.setMax("Dec");
//        xAxis.setTickFormat("%b");
        
        DateAxis axis = new DateAxis("Month");
        axis.setMax("Dec");
        axis.setTickFormat("%b");
        
       // animatedModel1.getAxes().put(AxisType.X, axis);
        
       animatedModel1.getAxes().put(AxisType.Y, yAxis);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
         yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
        System.out.println("np.info.dpshkhnl.bean.DashboardMB.createAnimatedModels()");
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Purchase");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Sales");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private LineChartModel initLinearModel() {
        System.out.println("np.info.dpshkhnl.bean.DashboardMB.initLinearModel(): "+x.size());
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
            series1.setLabel("Purchase");

        int b = 0;
        for (String i : x) {
            
            System.out.println("np.info.dpshkhnl.bean.DashboardMB.initLinearModel()"+ i);
            series1.set(i, b);
            b++;
            
        }
model.addSeries(series1);
        return model;
    }

    public ArrayList<String> getX() {
        return x;
    }

    public void setX(ArrayList<String> x) {
        this.x = x;
    }

    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }

}
