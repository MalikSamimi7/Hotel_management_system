package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class Dashboard implements Initializable {
    public Label todaybooks;
    public LineChart chart;
    @FXML
    private Label totalincome;
    @FXML
    private Label todayincome;
    @FXML
    private Label todaybook;
    dbconnector connector;
    Connection connection;
    int daybooks=0;


    public void setTodaybook() {

    }

    public void setTodayincome() {

    }

    public void setTotalincome() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setTodaybook();
        setTodayincome();
        setTotalincome();
    }

    private void getdate() throws SQLException {
        String date=java.time.LocalDate.now().toString();
        int t_total=0;
        int total=0;

        XYChart.Series series=new XYChart.Series();
        series.setName("Amount");
        LocalDate now=LocalDate.parse(date);

        connector=new dbconnector();
        connection=connector.getconnection();
        String sql="select * from customer";
        String dailyamount="SELECT * FROM dailyincome where dailyincome.date between (date_sub(curdate(),interval 6 day)) and CURDATE()";

        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {
         if(date.equals(rs.getString(6)))
         {
             t_total=t_total+Integer.parseInt(rs.getString(5));
             daybooks++;

         }
         total=total+Integer.parseInt(rs.getString(5));

        }
        todaybooks.setText(Integer.toString(daybooks));
        todayincome.setText(Integer.toString(t_total)+" af");
        totalincome.setText(Integer.toString(total)+" af");

        //showing chart
        LocalDate lastdate=now;
        ResultSet rs2=statement.executeQuery(dailyamount);

        while (rs2.next())
        {

            //series.getData().add(new XYChart.Data<>(rs2.getString(1),Integer.parseInt(rs2.getString(2))));
            lastdate=LocalDate.parse(rs2.getString(1));

        }
        if(lastdate.equals(null) || lastdate.equals(""))
        {
            lastdate=now;
        }
        System.out.println(lastdate);
        System.out.println(date);
        //if date changed add to database if not just update the amount


        if(!(lastdate.toString().equals(date)))
        {

            String insert="insert into dailyincome values ( '"+now+"', '"+t_total+"')";
            statement.executeUpdate(insert);
            System.out.println("entered");
        }
        else if(t_total!=0)
        {
            String update="update dailyincome set income='"+t_total+"' where date='"+date+"'  ";
            statement.executeUpdate(update);
        }
        ResultSet rs3=statement.executeQuery(dailyamount);
        while (rs3.next())
        {
            series.getData().add(new XYChart.Data<>(rs3.getString(1),Integer.parseInt(rs3.getString(2))));

        }

        chart.getData().add(series);

    }
}
