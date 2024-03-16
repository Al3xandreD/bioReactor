package org.openjfx.bioreactor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/** Représente un jumeau numérique de bio réacteur */
public class BioReactor {

    /** numero de port*/
    private int numPort=6666;

    /** température mesurée*/
    private double tempMes=0;

    /** température en consigne*/
    private double tempCons=0;

    /** température en commande*/
    private double tempComm=0;

    /** oxygène mesurée */
    private double oxyMes=0;

    /** ph mesurée*/
    private double phMes=0;

    private int indexRow=6;
    private Sheet firstSheet;

    public BioReactor() {

        // accessing the file
        try {
            String excelFilePath = "/Users/alexandredermouche/Documents/Alexandre /Cours/ENSTA/2A/STIC/conception logicielle/bioReactor/src/main/java/org/openjfx/bioreactor/2022-10-03-Act2-1.xlsx";
            InputStream inputStream = new FileInputStream(excelFilePath);

            Workbook myWb = new XSSFWorkbook(inputStream);  //new workbook
            firstSheet = myWb.getSheetAt(0);  //only one sheet

            // retrieving data
            this.tempMes = firstSheet.getRow(6).getCell(1).getNumericCellValue();
            this.tempCons= firstSheet.getRow(6).getCell(14).getNumericCellValue();
            this.tempComm= firstSheet.getRow(6).getCell(15).getNumericCellValue();
            this.oxyMes=firstSheet.getRow(6).getCell(16).getNumericCellValue();
            this.phMes=firstSheet.getRow(6).getCell(19).getNumericCellValue();

        } catch (IOException e){
            System.out.println("pas ouvert");
            e.printStackTrace();
        }

    }

//    public void openReactor(){
//        /**
//         * adds a TCP server to the bioreactor and starts it
//         */
//        this.serveurTCPS.add(new ServeurTCP(new ProtocoleSendState(),numPort, 10));
//        for (ServeurTCP s: serveurTCPS){
//            s.start();
//        }
//    }

    public void readingFile(){
        /**
         * Reads a row from the datafile according to a row index.
         * Row index is then incremented.
         */
        this.tempMes = firstSheet.getRow(indexRow).getCell(1).getNumericCellValue();
        this.tempCons= firstSheet.getRow(indexRow).getCell(14).getNumericCellValue();
        this.tempComm= firstSheet.getRow(indexRow).getCell(15).getNumericCellValue();
        this.oxyMes=firstSheet.getRow(indexRow).getCell(16).getNumericCellValue();
        this.phMes=firstSheet.getRow(indexRow).getCell(19).getNumericCellValue();
        indexRow++;
    }

    public void transmit(ArrayList<ServeurTCP> serveurTCPS){
        /**
         * Sends the state of the bioreactor to all TCP servers
         * Sent data takes the form of a String, using toString() method
         */
        int i;
        for(i=0; i<=5812;i++){
            this.readingFile();
            for(ServeurTCP s: serveurTCPS){
                s.saveData(this.toString());
            }
        System.out.println("Data was sent from bioreactor to all tcps");
        }
    }

    @Override
    public String toString() {
        return "BioReactor{" +
                ", tempMes=" + tempMes +
                ", tempCons=" + tempCons +
                ", tempComm=" + tempComm +
                ", oxyMes=" + oxyMes +
                ", phMes=" + phMes +
                '}';
    }


    // getters and setters



}
