package org.openjfx.bioreactor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.File;

/** Représente un jumeau numérique de bio réacteur */
public class BioReactor {
    private ArrayList<ServeurTCP> serveurTCPS=new ArrayList<>();

    /** température mesurée*/
    private float tempMes=0;

    /** température en consigne*/
    private float tempCons=0;

    /** température en commande*/
    private float tempComm=0;

    /** oxygène mesurée */
    private float oxyMes=0;

    /** ph mesurée*/
    private float phMes=0;

    public BioReactor() {

        try {
            String excelFilePath = "/Users/alexandredermouche/Documents/Alexandre /Cours/ENSTA/2A/STIC/conception logicielle/bioReactor/src/main/java/2022-10-03-Act2-1.xlsx";
            InputStream inputStream = new FileInputStream(excelFilePath);

            Workbook myWb = new XSSFWorkbook(inputStream);  //new workbook
            Sheet firstSheet = myWb.getSheetAt(0);  //only sheet

            // retrieving data
            this.tempMes = Float.parseFloat(firstSheet.getRow(1).getCell(7).getStringCellValue());

            //this.tempCons = tempCons;
//        this.tempComm = tempComm;
//        this.oxyMes = oxyMes;
//        this.phMes = phMes;
            System.out.println(this.tempMes);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void initReactor(){
    }
}
