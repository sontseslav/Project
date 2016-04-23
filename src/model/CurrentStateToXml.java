package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by coder on 02.08.15.
 */
public class CurrentStateToXml {
    //private String filePattern = "JATanks_turn_.xml";
    private int turn;
    private ArrayList<Tank> tanks;
    private static CurrentStateToXml instance;

    public CurrentStateToXml(Tank tank){
        tanks = tank.listOfEnemies;
    }

    public void processXml(){
        turn++;
        File xmlFile = new File(String.format("JATanks_turn_%s.xml",turn));
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(TankHuman.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(new TankHuman(),xmlFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
}
