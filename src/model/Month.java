package model;

public class Month{
    private int month,year;

    public Month(int month,int year){
        this.month=month;
        this.year=year;
    }
    public Month(Month d){
        this.month=d.month;
        this.year=d.year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static boolean is_greater_month(Month a,Month b)
    {
        boolean isGreater=false;
        if(a.year<b.year){
            isGreater= false;
        }
        else if(a.year>b.year){
            isGreater= true;
        }
        else {
            if(a.month>b.month){
                isGreater= true;
            }
            else if(a.month<b.month){
                isGreater= false;
            }
        }
        return isGreater;
    }

}
