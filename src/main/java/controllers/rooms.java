package controllers;

public class rooms {

    String Roomno;
    String Roomtype;
    String Roomstatus;
    String Price;

    public rooms(String  roomno, String roomtype, String roomstatus, String price) {
        Roomno = roomno;
        Roomtype = roomtype;
        Roomstatus = roomstatus;
        Price = price;
    }

    public void setRoomno(String roomno) {
        Roomno = roomno;
    }

    public void setRoomtype(String roomtype) {
        Roomtype = roomtype;
    }

    public void setRoomstatus(String roomstatus) {
        Roomstatus = roomstatus;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRoomno() {
        return Roomno;
    }

    public String getRoomtype() {
        return Roomtype;
    }

    public String getRoomstatus() {
        return Roomstatus;
    }

    public String getPrice() {
        return Price;
    }
}
