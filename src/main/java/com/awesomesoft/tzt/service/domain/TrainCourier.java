package com.awesomesoft.tzt.service.domain;

/**
 * Created by Erwin on 22-5-2014.
 */

import com.awesomesoft.tzt.service.exception.APIConnectionException;
import com.awesomesoft.tzt.service.exception.LocationUknownException;
import com.awesomesoft.tzt.web.PersonInfo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Een domein calsse waarvan een object opgeslagen moet worden in de database krijgt een @Entity tag. Let op gebruik geen hibernate.
@Entity
@DiscriminatorValue("TC")
public class TrainCourier extends Person{
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TrainTraject> planedTrajects = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TrainTraject asignedTraject;
    final static double SALARY = 5.0;
    protected TrainCourier() {
    }

    public TrainCourier(PersonInfo personInfo) throws LocationUknownException, APIConnectionException {
        super(personInfo);
    }

    public void planTraject(TrainTraject trainTraject) {
        asignedTraject = trainTraject;
        trainTraject.asignTrainCourier(this);
    }

    public List<TrainTraject> getPlanedTrajects() {
        return planedTrajects;
    }

    public boolean deletTraject(TrainTraject trainTraject) {
        if(planedTrajects.contains(trainTraject)){
            planedTrajects.remove(trainTraject);
            return true;
        }
        return false;
    }

    public void _ownTraject(TrainTraject trainTraject) {
        this.planedTrajects.add(trainTraject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainCourier)) return false;
        if (!super.equals(o)) return false;

        TrainCourier that = (TrainCourier) o;

        if (planedTrajects != null ? !planedTrajects.equals(that.planedTrajects) : that.planedTrajects != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (planedTrajects != null ? planedTrajects.hashCode() : 0);
        return result;
    }

    public double calcTotalTrajectPrice(Traject traject){
        return SALARY;
    }
}







//Een entity heeft altijd een id veld nodig dit is een privat long id met een tag @Id met een @GeneratedValue

//dat links met die menu's krijg ik ook niet goed.
//hij denkt gelijk dat je ingelogd bent als afzender
//Ja maar dat is nog even iets om te fixen dus. Ik zal even kijken waar dat zit.
//Nu heb ik de default constructor niet meer. Snap je waarom die er niet meer is?
//Ja overriding top klopt!


// Een entity moet altijd een consturctor hebben zonder argumenten dit is voor het JPA framework
//Wat we daarom doen is een protected constructor maken deze laat zien er niet mee gerodzooid moet worden.
//Wacht dit werkt niet, kijk maar.




// The magic is done/
//nu werkt het wel zo, dat moet ook volgens mij???
//Ja
//Het kwam door het id veld sorry:P
//Maar ik zit even met iets. Wij zetten nu op de pagina de persoon velden appart en die van de treinkoerier.
//Ja dat klopt volgens mij kan dat helemala nu niet.
//Nee dat moet namelijk via treinkoerier. Die gaan we namelijk persisten. Dus we moeten met objecten werken die JSF kan vullen en lezen. Deze moeten we in de registerr functie van de controller dan mapper naar de treinkoerier
//Maar zou ik daar over na denken?
//Ja dan kan ik nu orders maken ofzo? En het profiel wijzigen scherm afmaken?? met de ontbrekende velden.
// Ja klopt dat kan inderdaad.
//Ik heb geen login,die db is elke keer leeg.... Dat is ook een raar probleem dat opgelost moet zijn.
// oh wacht :P ga ik oplossen.





// Snap je dit tot zover?
// ja maar hoe zet ik nu een hele lijst met beschikbare tijden (dus maandag t/m zaterdag van zijn begin traject en terugwegtraject
//Dit is een Lijst van Trajecten.
// Ja maar dat koppel je toch niet want ik kan dat nergens normaal vinden, dat je trajecten aan een gebruiker koppelt...

// Nee je koppelt het niet maar de treinkoerier heeft treintrajecten. ik laat het je zien

//Ook hier geld weer gebruik de javax library





//We maken voor de 4 rollen dus allemaal domain classen aan?
//// Nee alleen als een rol echt speciefieke eigenschappen heet word het een domeinobject.
// een user kan alle rollen zijn. okee. Welke zou ik mee beginnen van die Excel lijst?
// Dan kan ik nu even snel typen en kijken of het goed is...
// Ik zou met deze beginen dat je een pagina hebt dat je speciaal kan registreren als trein koerier.
// laten we daarmee beginn. die heb ik al..
//Kan je het zover nog volgen?
// Ja ik moet hier nog paar veldjes bijdoen als IBAN en dan is het dus klaar en moet ik het maken voor afzender het zelfde?
// Maar afzender heeft een relatie van pakketjes (dat heeft TrainCourier ook) uhm ja? Oke zo bedoel je . Ja dat klopt een treinCourier moet een order uitvoeren.
//Een afzender maakt een order. Dit snap je neem ik aan?  en een order heeft een pakket. Ja dat bedeol ik ja....
///Wat is het probleem?  Daar moet ik dus het zelfde voor maken, maar denk wel hoe dat moet....
// Ik moet nog paar kleine puntjes en is die pagina klaar al.
//.. ah oke ik snap het..
//Laten we beginnen ookee moet ikh et doen of regel jij de laatste puntjes? dit is de pagina.


