package src;
/*
Lynda Ofume
B00738568
CSCI 4140 A4
December 3, 2022.
This program uses many functions emulate a multi-company service
 using X-Company, Y-Company, and Z-Company to satisfy the result to get a
 purchase order and parts list based on the outputted info using a Coordinator to log the record text file.
*/

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class A4 {
    public static void main(String[] args) throws Exception {

        String URL = "jdbc:mysql://localhost:3306/A4Database";
        String USER = "root";
        String PASSWORD = "Slammers65!";
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Scanner input;
        input = new Scanner(System.in);
        System.out.println("Welcome to Lynda's Purchase Order System (^_^) ");
        int companyNo = loginIn568();
        Thread.sleep(1500);


        while (true) {
            System.out.println("\nLet's get started, choose any number below to proceed...");
            System.out.println("1 - List Parts for sale");
            System.out.println("2 - Submit a PO");
            System.out.println("3 - List POs");
            System.out.println("4 - List lines for a given PO");
            System.out.println("Exit - Close the PO System");

            String choice = input.nextLine();

            //Function #1 to prints Parts for Sale to console
            if (choice.equals("1")) {
                ResultSet results;
                if (companyNo == 1) {
                    results = X_listParts568(conn);;
                } else if (companyNo == 2) {
                    results = Y_listParts568(conn);
                } else {
                    results = Z_listParts568(conn);
                }
                System.out.println(".....\n");
                System.out.println("partNo568, partDescription568, partName568, currentPrice568\n");
                int partID = 1;
                while (true) {
                    assert results != null;
                    if (!results.next()) break;
                    int partNo568 = partID;
                    String partDescription568 = results.getString("partDescription568");
                    String partName568 = results.getString("partName568");
                    int currentPrice568 = results.getInt("currentPrice568");
                    partID++;
                    System.out.println(partNo568 + " | " + partDescription568 + " | " + partName568 + " | " + currentPrice568 + "\n");
                }
                System.out.println(".....\n");
            }
            //Function #2 Helps a user Submit a Purchase order
            //Log Record Handling for submission of Company Z Purchase Order
            else if (choice.equals("2") && companyNo == 1) {
                System.out.println("Ready to Commit by X? (yes | no)");
                String yesX = input.nextLine();
                try {
                    FileWriter file = new FileWriter("logRecordX.txt", true);
                    PrintWriter writer = new PrintWriter(file);
                    int count = 1;
                    count++;

                    // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write("\n" + count + " | " + "Ready to Commit by X? | Response: " +yesX+ " | Logged: " + now);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (yesX.equals("no")) {
                    System.out.println("ABORT X");
                    try {
                        FileWriter file = new FileWriter("logRecordX.txt", true);
                        PrintWriter writer = new PrintWriter(file);
                        int count = 2;
                        count++;

                        // Save time after enter
                        LocalDateTime now = LocalDateTime.now();
                        writer.write("\n" + count + " | " + "ABORTED X " + " | " + now);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (yesX.equals("yes")) {
                    Thread.sleep(1500);
                    System.out.println("Enter your Client # to submit a purchase order:");
                    int clientNumb = Integer.parseInt(input.nextLine());
                    ArrayList<Integer> partID = new ArrayList<>();
                    ArrayList<Integer> partAmount = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter your Part ID # (ex. 12):");
                        partID.add(Integer.parseInt(input.nextLine()));
                        System.out.println("Enter quantity of parts you wish (ex. 300):");
                        partAmount.add(Integer.parseInt(input.nextLine()));
                        System.out.println("To finish your order enter \"done\" to submit:");
                        String continues = input.nextLine();
                        if (continues.equalsIgnoreCase("done")) {
                            break;
                        }
                    }
                    if (companyNo == 1) {
                        X_insertPO568(conn, clientNumb, partID, partAmount);
                    } else if (companyNo == 2) {
                        Y_insertPO568(conn, clientNumb, partID, partAmount);
                    } else {
                        Z_submitPO568(conn, clientNumb, partID, partAmount);
                    }
                }else{
                    break;
                }
            }
            //Log Record Handling for submission of Company Y Purchase Order
            else if (choice.equals("2") && companyNo == 2) {
                System.out.println("Ready to Commit by Y? (yes | no)");
                String yesX = input.nextLine();
                try {
                    FileWriter file = new FileWriter("logRecordY.txt", true);
                    PrintWriter writer = new PrintWriter(file);
                    int count = 1;
                    count++;

                    // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write("\n" + count + " | " + "Ready to Commit by Y? | Response: " +yesX+ " | Logged: " + now);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (yesX.equals("no")) {
                    System.out.println("ABORT Y");
                    try {
                        FileWriter file = new FileWriter("logRecordY.txt", true);
                        PrintWriter writer = new PrintWriter(file);
                        int count = 2;
                        count++;

                        // Save time after enter
                        LocalDateTime now = LocalDateTime.now();
                        writer.write("\n" + count + " | " + "ABORTED Y" + " | " + now);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (yesX.equals("yes")) {
                    Thread.sleep(1500);
                    System.out.println("Enter your Client # to submit a purchase order:");
                    int clientNumb = Integer.parseInt(input.nextLine());
                    ArrayList<Integer> partID = new ArrayList<>();
                    ArrayList<Integer> partAmount = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter your Part ID # (ex. 12):");
                        partID.add(Integer.parseInt(input.nextLine()));
                        System.out.println("Enter quantity of parts you wish (ex. 300):");
                        partAmount.add(Integer.parseInt(input.nextLine()));
                        System.out.println("To finish your order enter \"done\" to submit:");
                        String continues = input.nextLine();
                        if (continues.equalsIgnoreCase("done")) {
                            break;
                        }
                    }
                    if (companyNo == 1) {
                        X_insertPO568(conn, clientNumb, partID, partAmount);
                    } else if (companyNo == 2) {
                        Y_insertPO568(conn, clientNumb, partID, partAmount);
                    } else {
                        Z_submitPO568(conn, clientNumb, partID, partAmount);
                    }
                }else{
                    break;
                }
            }
            //Log Record Handling for submission of Company Z Purchase Order
            else if (choice.equals("2") && companyNo == 3) {
                System.out.println("Ready to Commit by Z? (yes | no)");
                String yesX = input.nextLine();
                try {
                    FileWriter file = new FileWriter("logRecordZ.txt", true);
                    PrintWriter writer = new PrintWriter(file);
                    int count = 1;
                    count++;

                    // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write("\n" + count + " | " + "Ready to Commit by Z? | Response: " +yesX+ " | Logged: " + now);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (yesX.equals("no")) {
                    System.out.println("ABORT Z");
                    try {
                        FileWriter file = new FileWriter("logRecordZ.txt", true);
                        PrintWriter writer = new PrintWriter(file);
                        int count = 2;
                        count++;

                        // Save time after enter
                        LocalDateTime now = LocalDateTime.now();
                        writer.write("\n" + count + " | " + "ABORTED Z" + " | " + now);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if (yesX.equals("yes")) {
                    Thread.sleep(1500);
                    System.out.println("Enter your Client # to submit a purchase order:");
                    int clientNumb = Integer.parseInt(input.nextLine());
                    ArrayList<Integer> partID = new ArrayList<>();
                    ArrayList<Integer> partAmount = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter your Part ID # (ex. 12):");
                        partID.add(Integer.parseInt(input.nextLine()));
                        System.out.println("Enter quantity of parts you wish (ex. 300):");
                        partAmount.add(Integer.parseInt(input.nextLine()));
                        System.out.println("To finish your order enter \"done\" to submit:");
                        String continues = input.nextLine();
                        if (continues.equalsIgnoreCase("done")) {
                            break;
                        }
                    }
                    if (companyNo == 1) {
                        X_insertPO568(conn, clientNumb, partID, partAmount);
                    } else if (companyNo == 2) {
                        Y_insertPO568(conn, clientNumb, partID, partAmount);
                    } else {
                        Z_submitPO568(conn, clientNumb, partID, partAmount);
                    }
                }else{
                    break;
                }
            }
            //Function #3 helps the user list Purchase Order
            else if (choice.equals("3")) {
                System.out.println("Please enter client ID to get a list of Purchase Orders");
                String temp = input.nextLine();
                int id = Integer.parseInt(temp);
                ResultSet results;
                if (companyNo == 1) {
                    results = X_ListPO568(conn, id);
                } else if (companyNo == 2) {
                    results = Y_ListPO568(conn, id);
                } else {
                    results = Z_ListPO568(conn, id);
                }
                System.out.println(".....\n");
                System.out.println("poNo568, datePO568, status568, clientId568\n");
                while (true) {
                    assert results != null;
                    if (!results.next()) break;
                    int poNo568 = results.getInt("poNo568");
                    Date datePO568 = results.getDate("datePO568");
                    String status568 = results.getString("status568");
                    int clientId568 = results.getInt("clientId568");
                    System.out.println(poNo568 + " | " + datePO568 + " | " + status568 + " | " + clientId568 + "\n");
                }
                System.out.println(".....\n");
            }
            //Function #4 list Lines of a Purchase Order
            else if (choice.equals("4")) {
                System.out.println("Enter Purchase Order # to continue browsing...");
                String t = input.nextLine();
                int num = Integer.parseInt(t);
                ResultSet results;
                if (companyNo == 1) {
                    results = X_listThePO568(conn, num);
                } else if (companyNo == 2) {
                    results = Y_listThePO568(conn, num);
                } else {
                    results = Z_listThePO568(conn, num);
                }
                System.out.println(".....\n");
                if (companyNo == 1 || companyNo == 2) {
                    System.out.println("poNo568, lineNo568, partNo568, partPrice568, partQuantity568\n");
                    while (true) {
                        assert results != null;
                        if (!results.next()) break;
                        int poNo568 = results.getInt("poNo568");
                        int lineNo568 = results.getInt("lineNo568");
                        int partNo568 = results.getInt("partNo568");
                        int partPrice568 = results.getInt("partPrice568");
                        int partQuantity568 = results.getInt("partQuantity568");

                        System.out.println(poNo568 + " | " + lineNo568 + " | " + partNo568 + " | " + partPrice568
                                + " | " + partQuantity568 + "\n");
                    }
                } else {
                    System.out.println("poNo568, lineNo568, Company568, partNo568, partPrice568, partQuantity568\n");
                    while (true) {
                        assert results != null;
                        if (!results.next()) break;
                        int poNo568 = results.getInt("poNo568");
                        int lineNo568 = results.getInt("lineNo568");
                        String Company568 = results.getString("Company568");
                        int partNo568 = results.getInt("partNo568");
                        int partPrice568 = results.getInt("partPrice568");
                        int partQuantity568 = results.getInt("partQuantity568");
                        // print the results
                        System.out.println(poNo568 + " | " + lineNo568 + " | " + Company568
                                + " | " + partNo568 + " | " + partPrice568 + " | " + partQuantity568 + "\n");
                    }
                }
                System.out.println(".....\n");
            } else if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for your service, bye for now!");
                input.close();
                assert conn != null;
                conn.close();
                System.exit(0);
            }
        }
    }


    static int loginIn568() throws FileNotFoundException, InterruptedException {
        Scanner loginValue = new Scanner(System.in);
        while (true) {
            System.out.println("Please select the company you wish to browse by asking the question:");
            System.out.println("1 - X-Company");
            System.out.println("2 - Y-Company");
            System.out.println("3 - Z-Company");
            System.out.println("EXIT - End Session");
            String choice = loginValue.nextLine();
            if (choice.equals("1")) {
                System.out.println("Get Ready X");
                Thread.sleep(1000);
                //Log Record X
                try{
                    File file = new File("logRecordX.txt");
                    PrintWriter writer = new PrintWriter(file);
                    int count = 0;
                    count++;
                     // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write( count + " | " + "Get Ready X" + " | " + now);
                    writer.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
                return 1;
            } else if (choice.equals("2")) {
                System.out.println("Get Ready Y");
                Thread.sleep(1000);
                {
                    File file = new File("logRecordY.txt");
                    PrintWriter writer = new PrintWriter(file);
                    int count = 0;
                    count++;
                    // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write( count + " | " + "Get Ready Y" + " | " + now);
                    writer.close();
                }
                return 2;
            } else if (choice.equals("3")) {
                System.out.println("Get Ready Z");
                Thread.sleep(1000);
                try{
                    File file = new File("logRecordZ.txt");
                    PrintWriter writer = new PrintWriter(file);
                    int count = 0;
                    int value = count +1;
                    // Save time after enter
                    LocalDateTime now = LocalDateTime.now();
                    writer.write( value + " | " + "Get Ready Z" + " | " + now);
                    writer.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
                return 3;
            } else if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for visiting, bye bye now!");
                loginValue.close();
                System.exit(0);
            }
        }
    }

    //----X-COMPANY functionality----//

    // List all the current available parts



    static ResultSet X_listParts568(Connection con568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("Select * FROM `x-parts568`");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    // List all PO's with a certain client ID
    static ResultSet X_ListPO568(Connection con568, int ID568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `x-pos568` WHERE clientId568 = " + ID568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    // Submit a purchase order functionality
    static void X_insertPO568(Connection con568, int clientNo568, ArrayList<Integer> partID568, ArrayList<Integer> partAmt568) {
        try {
            Statement st = con568.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM `x-clients568` WHERE clientId568 = " + clientNo568);
            if (!results.next()) {
                System.out.println("Need to create client # before proceeding...");
                return;
            }
            Set<Integer> set = new HashSet<>(partID568);
            if (set.size() < partID568.size()) {
                System.out.println("No duplicate orders please... try again!");
                return;
            }
            for (int i = 0; i < partID568.size(); i++) {
                ResultSet QoHCheck = st.executeQuery("SELECT * FROM `x-parts568` WHERE partNo568 = " + partID568.get(i));
                if (!QoHCheck.next()) {
                    System.out.println("Part ID must match the same in Parts List...");
                    return;
                }
                int QoHCheckIt = QoHCheck.getInt("QoH568");
                if (QoHCheckIt < partAmt568.get(i)) {
                    System.out.println("Your quantity <= quantity available at time of order...");
                    return;
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            st.executeUpdate("INSERT INTO `x-pos568` (datePO568, status568, clientID568) " + "VALUES (\"" + dtf.format(now) +
                    "\", 'Incomplete', " + clientNo568 + ")");
            ResultSet poNum = st.executeQuery("SELECT MAX(poNo568) FROM `x-pos568`");
            poNum.next();
            int poCheckIt = poNum.getInt("MAX(poNo568)");
            for (int i = 0; i < partID568.size(); i++) {
                ResultSet price = st.executeQuery("SELECT * FROM `x-parts568` WHERE partNo568 = " + partID568.get(i));
                price.next();
                int pricePerm = price.getInt("currentPrice568");
                st.executeUpdate("INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568) " +
                        "" + "VALUES (" + poCheckIt + ", " + (i + 1) + "," + partID568.get(i) + " , " + pricePerm + ", " + partAmt568.get(i) + ")");
            }
            System.out.println("Complete!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // List all Lines with a given PO number
    static ResultSet X_listThePO568(Connection con568, int no568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `x-line568` WHERE poNo568 = " + no568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    //----Y-Company functionality----//

    // Submit a po in two parts
    static void Y_insertPO568(Connection con568, int clientNo568, ArrayList<Integer> partID568, ArrayList<Integer> partAmt568) {
        try {
            Statement st = con568.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM `y-clients568` WHERE clientId568 = " + clientNo568);
            if (!results.next()) {
                System.out.println("Need to create client # before proceeding...");
                return;
            }
            Set<Integer> set = new HashSet<>(partID568);
            if (set.size() < partID568.size()) {
                System.out.println("No duplicate orders please... try again!");
                return;
            }
            for (int i = 0; i < partID568.size(); i++) {
                ResultSet QoHCheck = st.executeQuery("SELECT * FROM `y-parts568` WHERE partNo568 = " + partID568.get(i));
                if (!QoHCheck.next()) {
                    System.out.println("Part ID must match the same in Parts List...");
                    return;
                }
                int QoHCheckIt = QoHCheck.getInt("QoH568");
                if (QoHCheckIt < partAmt568.get(i)) {
                    System.out.println("Your quantity <= quantity available at time of order...");
                    return;
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            st.executeUpdate("INSERT INTO `y-pos568` (datePO568, status568, clientID568) " + "VALUES (\""
                    + dtf.format(now) + "\", 'Incomplete', " + clientNo568 + ")");
            ResultSet poNum = st.executeQuery("SELECT MAX(poNo568) FROM `y-pos568`");
            poNum.next();
            int poCheckIt = poNum.getInt("MAX(poNo568)");

            for (int i = 0; i < partID568.size(); i++) {
                ResultSet price = st.executeQuery("SELECT * FROM `y-parts568` WHERE partNo568 = " + partID568.get(i));
                price.next();
                int priceIt = price.getInt("currentPrice568");

                st.executeUpdate("INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568) " +
                        "" + "VALUES (" + poCheckIt + ", " + (i + 1) + "," + partID568.get(i) + " , " + priceIt + ", " + partAmt568.get(i) + ")");
            }
            System.out.println("Complete!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // List all the current available parts
    static ResultSet Y_listParts568(Connection con568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("Select * from `y-parts568`");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    // List all PO's with a certain client ID
    static ResultSet Y_ListPO568(Connection con568, int ID568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `y-pos568` WHERE clientId568 = " + ID568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    // List all Lines with a given PO number
    static ResultSet Y_listThePO568(Connection con568, int no568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `y-line568` WHERE poNo568 = " + no568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    //----Z-Company functionality----//

    // List all the current available parts for Z-company
    static ResultSet Z_listParts568(Connection con568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM a4Database.`y-parts568` UNION SELECT * FROM a4Database.`x-parts568` " +
                    "where partDescription568 NOT IN (SELECT partDescription568 FROM a4Database.`y-parts568`)");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    // Add PO for Z-company
    static void Z_submitPO568(Connection con568, int clientNo568, ArrayList<Integer> partID568, ArrayList<Integer> partAmt568) {
        try {
            Statement st = con568.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM `z-clients568` WHERE clientId568 = " + clientNo568);
            if (!results.next()) {
                System.out.println("Need to create client # before proceeding...");
                return;
            }
            Set<Integer> set = new HashSet<>(partID568);
            if (set.size() < partID568.size()) {
                System.out.println("No duplicate orders please... try again!");
                return;
            }
            ArrayList<Boolean> selectDB = new ArrayList<>();
            ArrayList<Integer> findID = new ArrayList<>();
            for (int i = 0; i < partID568.size(); i++) {
                String PartDesc;
                ResultSet reference = st.executeQuery("SELECT * FROM a4Database.`y-parts568` UNION SELECT * FROM " +
                        "a4Database.`x-parts568` WHERE partDescription568 NOT IN (SELECT partDescription568 FROM a4Database.`y-parts568`)");
                for (int k = 0; k < partID568.get(i); k++) {
                    reference.next();
                }
                PartDesc = reference.getString("partDescription568");
                ResultSet QoHCheckX = st.executeQuery("SELECT * FROM `x-parts568` WHERE partDescription568 = '" + PartDesc + "'");
                boolean PartFoundX;
                int PriceX = 0;
                int NoX = 0;

                if (!QoHCheckX.next()) {
                    PartFoundX = false;
                } else {
                    PartFoundX = true;
                    PriceX = QoHCheckX.getInt("currentPrice568");
                    NoX = QoHCheckX.getInt("QoH568");
                }
                ResultSet QoHCheckY = st.executeQuery("SELECT * FROM `y-parts568` WHERE partDescription568 = '" + PartDesc + "'");
                boolean PartFoundY;
                int NoY = 0;
                int PriceY = 0;

                if (!QoHCheckY.next()) {
                    PartFoundY = false;
                } else {
                    PartFoundY = true;
                    NoY = QoHCheckY.getInt("QoH568");
                    PriceY = QoHCheckY.getInt("currentPrice568");
                }
                if (!PartFoundX && !PartFoundY) {
                    System.out.println("Part ID must match the same in Parts List...");
                    return;
                }
                int QoHCheckIt;
                boolean UseDBX;
                if (PartFoundX && PartFoundY) {
                    if (PriceX < PriceY) {
                        UseDBX = true;
                        QoHCheckIt = NoX;
                    } else if (PriceX > PriceY) {
                        UseDBX = false;
                        QoHCheckIt = NoY;
                    } else {
                        if (NoX > NoY) {
                            UseDBX = true;
                            QoHCheckIt = NoX;
                        } else {
                            UseDBX = false;
                            QoHCheckIt = NoY;
                        }
                    }
                } else if (PartFoundX) {
                    QoHCheckIt = NoX;
                    UseDBX = true;
                } else {
                    QoHCheckIt = NoY;
                    UseDBX = false;
                }

                if (QoHCheckIt < partAmt568.get(i)) {
                    System.out.println("Enter correct quantity parts in your Purchase order...");
                    return;
                }
                selectDB.add(UseDBX);
                ResultSet getLocalDBID;
                if (UseDBX) {
                    getLocalDBID = st.executeQuery("SELECT * FROM `x-parts568` WHERE partDescription568 = '" + PartDesc + "'");
                } else {
                    getLocalDBID = st.executeQuery("SELECT * FROM `y-parts568` WHERE partDescription568 = '" + PartDesc + "'");
                }
                getLocalDBID.next();
                findID.add(getLocalDBID.getInt("partNo568"));

            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            st.executeUpdate("INSERT INTO `z-pos568` (datePO568, status568, clientID568) " + "VALUES " +
                    "(\"" + dtf.format(now) + "\", 'Incomplete', " + clientNo568 + ")");
            ResultSet poNum = st.executeQuery("SELECT MAX(poNo568) FROM `z-pos568`");
            poNum.next();
            int poCheck = poNum.getInt("MAX(poNo568)");

            ArrayList<Integer> DatabaseXLocalIds = new ArrayList<>();
            ArrayList<Integer> DatabaseXLocalAmounts = new ArrayList<>();
            ArrayList<Integer> DatabaseYLocalIds = new ArrayList<>();
            ArrayList<Integer> DatabaseYLocalAmounts = new ArrayList<>();
            boolean NeedToPODatabaseX = false;
            boolean NeedToPODatabaseY = false;
            for (int i = 0; i < findID.size(); i++) {
                if (selectDB.get(i)) {
                    NeedToPODatabaseX = true;
                    ResultSet price = st.executeQuery("SELECT * FROM `x-parts568` WHERE partNo568 = " + findID.get(i));
                    price.next();
                    int priceIt = price.getInt("currentPrice568");
                    st.executeUpdate("INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, partPrice568, partQuantity568) " +
                            "" + "VALUES (" + poCheck + ", " + (i + 1) + ", 'X' ," + findID.get(i) + " , " + priceIt + ", " + partAmt568.get(i) + ")");
                    DatabaseXLocalIds.add(findID.get(i));
                    DatabaseXLocalAmounts.add(partAmt568.get(i));
                } else {
                    NeedToPODatabaseY = true;
                    ResultSet price = st.executeQuery("SELECT * FROM `y-parts568` WHERE partNo568 = " + findID.get(i));
                    price.next();
                    int priceIt = price.getInt("currentPrice568");
                    st.executeUpdate("INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, " +
                            "partPrice568, partQuantity568) " + "VALUES (" + poCheck + ", " + (i + 1) + ",'Y' ,"
                            + findID.get(i) + " , " + priceIt + ", " + partAmt568.get(i) + ")");
                    DatabaseYLocalIds.add(findID.get(i));
                    DatabaseYLocalAmounts.add(partAmt568.get(i));
                }
            }
            if (NeedToPODatabaseX) {
                X_insertPO568(con568, 4, DatabaseXLocalIds, DatabaseXLocalAmounts);
            }
            if (NeedToPODatabaseY) {
                Y_insertPO568(con568, 4, DatabaseYLocalIds, DatabaseYLocalAmounts);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // List all Purchase order with proper client ID
    static ResultSet Z_ListPO568(Connection con568, int ID568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `z-pos568` WHERE clientId568 = " + ID568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
    static ResultSet Z_listThePO568(Connection con568, int nO568) {
        try {
            Statement st = con568.createStatement();
            return st.executeQuery("SELECT * FROM `z-line568` WHERE poNo568 = " + nO568);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
}