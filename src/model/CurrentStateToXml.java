package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by coder on 02.08.15.
 */
public class CurrentStateToXml {
    //private String filePattern = "JATanks_turn_.xml";
    private int turn;
    private BattleField battleField;
    private static CurrentStateToXml instance;

    private CurrentStateToXml(){
        battleField = BattleField.getInstance();
    }

    public static CurrentStateToXml getInstance(){
        if (instance == null){
            instance = new CurrentStateToXml();
        }
        return instance;
    }

    public void processXml(){
        turn++;
        File xmlFile = new File(String.format("JATanks_turn_%s.xml",turn));
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(BattleField.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(new BattleField(),xmlFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
}
