//Lynda Ofume
//B00738568
//CSCI 4140 A2
//October 17, 2022
//This program uses many function to get a purchase order and parts list based on the outputted data

import java.util.*;
import java.util.Scanner;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class A2 {
        static final String DB_URL = "jdbc:mysql://localhost:3306/pO";
        static final String username = "root";
        static final String password = "Slammers65!";

        public static void main(String[] args){
            Connection conn = null;
            try{
                conn = DriverManager.getConnection(DB_URL, username, password);
            }catch(Exception ex) {
                System.err.println(ex);
            }

            try (Scanner in = new Scanner(System.in)) {
                while (true){
                    System.out.println("To get Parts List type 1");
                    System.out.println("To Submit Purchase Order type 2");
                    System.out.println("To get List of Purchase Orders based on Client ID type 3");
                    System.out.println("To get List of Lines based on PO# type 4");
                    System.out.println("To quit this program enter quit");
                    String select = in.nextLine();

                    //FUNCTION #1 to print part list
                    if(select.equals("1")) {
                        ResultSet display = function1_568(conn);
                        System.out.println("PARTS FOR SALE:\n");
                        try {
                            while (display != null && display.next()){
                                double priced568 = display.getDouble("currentPrice568");
                                String name568 = display.getString("partName568");
                                System.out.println("Part Name: " + name568  + ", Price: $" + priced568 +"\n");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    //FUNCTION 2: Submit Purchase Order
                    else if(select.equals("2")) {
                        System.out.println("ENTER CLIENT ID:");
                        int clientNumber = Integer.parseInt(in.nextLine());
                        ArrayList<Integer> poNos = new ArrayList<>();
                        ArrayList<Integer> amt = new ArrayList<>();
                        ArrayList<Double> price = new ArrayList<>();

                        while(true){
                            System.out.println("ENTER PART#: ");
                            poNos.add(Integer.parseInt(in.nextLine()));

                            System.out.println("ENTER DATE? ");
                            in.nextLine();

                            System.out.println("WHAT QTY DO YOU WANT?");
                            amt.add(Integer.parseInt(in.nextLine()));

                            System.out.println("WHAT AMOUNT CAN YOU FULFILL?");
                            price.add(Double.parseDouble(in.nextLine()));

                            System.out.println("DO YOU WANT TO ADD MORE TO YOUR ORDER");
                            String continues = in.nextLine();
                            if(!continues.equalsIgnoreCase("yes")) {
                                break;
                            }
                        }
                        function2_568(conn,clientNumber,poNos,amt,price);
                    }

                    //FUNCTION 3: RETURN PURCHASE ORDER
                    else if(select.equals("3")) {
                        System.out.println("ENTER YOUR CLIENT ID:");
                        int id = Integer.parseInt(in.nextLine());
                        ResultSet answers = function3_568(conn, id);
                        System.out.println("HERE IS YOUR PURCHASE ORDER RECEIPT:\n");
                        try {
                            while (answers != null && answers.next()){
                                int no568 = answers.getInt("poNo568");
                                int id568 = answers.getInt("clientID568");
                                Date date568 = answers.getDate("datePO568");
                                String status568 = answers.getString("status568");
                                System.out.println("Purchase#: " + no568 + ", Date: "
                                        + date568 + ", Status: " + status568 + ", ClientID: " + id568 +"\n");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    //FUNCTION #4: RETURN PO ON LINES
                    else if(select.equals("4")) {
                        System.out.println("ENTER YOUR PURCHASE ORDER NUMBER:");
                        int po = Integer.parseInt(in.nextLine());
                        ResultSet answers = function4_568(conn, po);
                        System.out.println("VIEW YOUR ORDER BELOW -------\n");
                        try {
                            while (answers != null && answers.next()){
                                int pNo568 = answers.getInt("poNo568");
                                int lNo568 = answers.getInt("lineNo568");
                                double price568 = answers.getDouble("priceOrdered568");
                                int amount568 = answers.getInt("qty568");
                                // print the results
                                System.out.println("PO#: " + pNo568 + ", " + "Line#: "  + lNo568
                                        + ", " + "Price: $" + price568 + ", Quantity: " + amount568 +"\n");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else if(select.equalsIgnoreCase("quit")) {
                        System.out.println("THANK YOU FOR USING OUR SYSTEM, HAVE A GREAT DAY!");
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //function 1
        public static ResultSet function1_568 (Connection conn){
            try {
                //Preparing statement
                Statement st = conn.createStatement();
                return st.executeQuery("Select partName568, currentPrice568, partDescription568 from Parts568");
            }
            catch (Exception ex) {
                System.err.println(ex);
                return null;
            }
        }
        //function 2
        public static void function2_568 (Connection conn,int clientNumber,ArrayList poNos, ArrayList amt, ArrayList price){
            try{
                Statement st = conn.createStatement();
                ResultSet clientCheck = st.executeQuery("SELECT * FROM Clients568 where clientId568 = " + clientNumber);
                if(!clientCheck.next()){
                    System.out.println("ERROR: Not a client");
                    return;
                }
                for(int w = 0; w < poNos.size(); w++){
                    ResultSet partCheck = st.executeQuery("SELECT * FROM Parts568 where partNo568 = " + poNos.get(w));
                    if(!partCheck.next()){
                        System.out.println("ERROR: Wrong part ID");
                        return;
                    }
                    int amountHave = partCheck.getInt("qoh568");
                    if(amountHave < (int)amt.get(w)){
                        System.out.println("ERROR: Can not fill that amount");
                        return;
                    }
                    double priceHave = partCheck.getDouble("currentPrice568");
                    if(priceHave != (double)price.get(w)){
                        System.out.println("Error: Make sure price is same as part");
                        return;
                    }
                }
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                ResultSet poNo = st.executeQuery("SELECT MAX(poNo568) FROM PO568");
                int poNum = 1;
                if(poNo.next()){
                    if(poNum <= poNo.getInt("MAX(poNo568)")){
                        poNum = poNo.getInt("MAX(poNo568)")+ 1;
                    }
                }
                st.executeUpdate("INSERT INTO PO (poNo568, datePO568, clientID568)"
                        + "VALUES ("+  poNum + " , \"" + format.format(now) + "\", 'In progress', "+ clientNumber +")");
                for(int y = 0; y < poNos.size(); y++){
                    st.executeUpdate("INSERT INTO Lines568 (lineNo568, poNo568, partNo568, priceOrdered568, qty568) "
                            +"VALUES (" + (y+1) + ", " + poNum + "," + poNos.get(y) + " , "+ price.get(y) +", " + amt.get(y) + ")");
                }
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        //function 3
        public static ResultSet function3_568 (Connection conn,int id){
            try {
                //Preparing statement
                Statement st = conn.createStatement();
                return st.executeQuery("SELECT * FROM PO568 where clientID568 = " + id);
            }
            catch (Exception ex) {
                System.err.println(ex);
                return null;
            }
        }
        //function 4
        public static ResultSet function4_568 (Connection conn,int po){
            try {
                //Preparing statement
                Statement st = conn.createStatement();
                return st.executeQuery("SELECT * FROM Lines568 where poNo568 = " + po);
            }
            catch (Exception ex) {
                System.err.println(ex);
                return null;
            }
        }
}

