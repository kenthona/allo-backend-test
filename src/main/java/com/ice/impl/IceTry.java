package com.ice.impl;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;

import java.awt.*;

public class IceTry {

    public static void main(String[] args) {

        //Create a Document object
        Document document = new Document();

        //Add a section
        Section section = document.addSection();

        //Define the data for table
        String[] header = {"Name", "Capital", "Continent", "Area", "Population"};
        String[][] data =
                {
                        new String[]{"Argentina", "Buenos Aires", "South America", "2777815", "32300003"},
                        new String[]{"Bolivia", "La Paz", "South America", "1098575", "7300000"},
                        new String[]{"Brazil", "Brasilia", "South America", "8511196", "150400000"},
                        new String[]{"Canada", "Ottawa", "North America", "9976147", "26500000"},
                        new String[]{"Chile", "Santiago", "South America", "756943", "13200000"},
                        new String[]{"Colombia", "Bogota", "South America", "1138907", "33000000"},
                        new String[]{"Cuba", "Havana", "North America", "114524", "10600000"},
                        new String[]{"Ecuador", "Quito", "South America", "455502", "10600000"},
                        new String[]{"El Salvador", "San Salvador", "North America", "20865", "5300000"},
                        new String[]{"Guyana", "Georgetown", "South America", "214969", "800000"},

                };

        //Add a table
        Table table = section.addTable(true);
        table.resetCells(data.length + 1, header.length);

        //Set the first row as table header
        TableRow row = table.getRows().get(0);
        row.isHeader(true);
        row.setHeight(20);
        row.setHeightType(TableRowHeightType.Exactly);
        for (int j = 0; j < row.getCells().getCount(); j++) {
            row.getCells().get(j).getCellFormat().getShading().setBackgroundPatternColor(Color.gray);
        }
        for (int i = 0; i < header.length; i++) {
            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //Add data to the rest of rows
        for (int r = 0; r < data.length; r++) {
            TableRow dataRow = table.getRows().get(r + 1);
            dataRow.setHeight(25);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            for (int c = 0; c < dataRow.getCells().getCount(); c++) {
                dataRow.getCells().get(c).getCellFormat().getShading().setBackgroundPatternColor(Color.white);
            }
            for (int c = 0; c < data[r].length; c++) {
                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
            }
        }

        //Set background color for cells
        for (int j = 1; j < table.getRows().getCount(); j++) {
            if (j % 2 == 0) {
                TableRow row2 = table.getRows().get(j);
                for (int f = 0; f < row2.getCells().getCount(); f++) {
                    row2.getCells().get(f).getCellFormat().getShading().setBackgroundPatternColor(new Color(173, 216, 230));
                }
            }
        }

        //Save to file
        document.saveToFile("output/CreateTable.docx", FileFormat.Docx_2013);
    }
}
