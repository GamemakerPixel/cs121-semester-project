package week14.dateFormatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TotalCalories{
  public static class DietInfo{
    private int caloriesPerDay;
    private int totalDays;
    private long totalCalories;

    public DietInfo(int caloriesPerDay, int totalDays){
      this.caloriesPerDay = caloriesPerDay;
      this.totalDays = totalDays;
      this.totalCalories = caloriesPerDay * totalDays;
    }

    public int getCaloriesPerDay(){
      return caloriesPerDay;
    }

    public int getTotalDays(){
      return totalDays;
    }

    public long getTotalCalories(){
      return totalCalories;
    }
  }

  private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

  public static DietInfo calculateDietInfo(int caloriesPerDay, String startDateString, String endDateString) {
    Date startDate;
    Date endDate;

    try{
      startDate = dateFormat.parse(startDateString);
      endDate = dateFormat.parse(endDateString);
    }
    catch (ParseException exception){
      System.out.println("Invalid date format.");
      return null;
    }

    long millisecondDifference = endDate.getTime() - startDate.getTime();

    if (millisecondDifference < 0){
      System.out.println("Diet cannot end before it starts!");
      return null;
    }

    int daysDifference = (int) TimeUnit.DAYS.convert(millisecondDifference, TimeUnit.MILLISECONDS);

    return new DietInfo(caloriesPerDay, daysDifference);
  }
}
