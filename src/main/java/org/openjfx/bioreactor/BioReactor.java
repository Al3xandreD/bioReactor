package org.openjfx.bioreactor;

import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import java.io.File;

public class BioReactor {
    private ArrayList<ServeurTCP> serveurTCPS=new ArrayList<>();
    private float tempMes=0;
    private float tempCons=0;
    private float tempComm=0;
    private float oxyMes=0;
    private float phMes=0;

    public BioReactor(ArrayList<ServeurTCP> serveurTCPS, float tempMes, float tempCons, float tempComm, float oxyMes, float phMes) {
        this.serveurTCPS = serveurTCPS;
        this.tempMes = tempMes;
        this.tempCons = tempCons;
        this.tempComm = tempComm;
        this.oxyMes = oxyMes;
        this.phMes = phMes;
    }

    public void initReactor(FileInputStream path){
        Workbook myWb = new WorkbookFactory.create(path);
        Sheet firstSheet=myWb.getSheetAt(0);
        this.tempMes=firstSheet.getN
    }
}
