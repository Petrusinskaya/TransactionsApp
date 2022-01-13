package util;

public class ExcelUtil {
    /*
    //Connecting to excel file
        //FileInputStream file = new FileInputStream(new File());
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        Row header = sheet.createRow(0);
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Date");
        headerCell = header.createCell(1);
        headerCell.setCellValue("Type");

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("06/11/2021");
        cell = row.createCell(1);
        cell.setCellValue("Expense");

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        //workbook.write();
        workbook.close();
        /*
        //Create the data for the excel sheet
        Map&lt;string, object[]=""&gt; data = new TreeMap&lt;string, object[]=""&gt;();
        data.put("1", new Object[] {"ID", "FIRSTNAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Randy", "Maven"});
        data.put("3", new Object[] {2, "Raymond", "Smith"});
        data.put("4", new Object[] {3, "Dinesh", "Arora"});
        data.put("5", new Object[] {4, "Barbra", "Klien"});

        //Iterate over data and write it to the sheet
        Set keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        //Save the excel sheet
        try{
            FileOutputStream out = new FileOutputStream(
                    new File("c:\Dinesh\javahabitExcelDemo.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("javahabitExcelDemo.xlsx Successfully created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        /*sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("Hello");
        sheet.getRow(0).createCell(1).setCellValue("world");

        sheet.createRow(1);
        sheet.getRow(1).createCell(0).setCellValue("Привет");
        sheet.getRow(1).createCell(1).setCellValue("мир");

        File file = new File("/Users/petrusinskaya_alice/IdeaProjects/Transactions App/ExcelFiles");

        workbook.write(file);
        workbook.close();*/
}
