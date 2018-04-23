package main;


import core.Credit;
import core.CreditException;
import l2fx.MouseButtonEvent;
import l2fx.Composant;
import l2fx.Event;
import l2fx.Handler;


class HandlerPlus implements Handler {
	
	private  Credit	credit;
	
	public HandlerPlus(Credit credit) {
		this.credit = credit;
	}
	
	@Override
	public boolean canHandle(Event e) {
		if (e instanceof MouseButtonEvent)
			return true;
		return false;
	}

	@Override
	public void handle(Event e) {
		credit.setCapital(credit.getCapital()+1000);
	}
}

class HandlerMoins implements Handler {
	
	private  Credit	credit;
	
	public HandlerMoins(Credit credit) {
		this.credit = credit;
	}
	
	@Override
	public boolean canHandle(Event e) {
		if (e instanceof MouseButtonEvent)
			return true;
		return false;
	}

	@Override
	public void handle(Event e) {
		credit.setCapital(credit.getCapital()-1000);
	}
}

public class Application {
	
	private static Composant plus;
	private static Composant moins;


	public static void main(String[] args) {
		Credit credit = null;
		try {
			credit = new Credit(Credit.TAUX_NORMAL, 120, 10000);
		} catch (CreditException e) {
			// ne devrait pas se produire
			e.printStackTrace();
		}
		
				
		plus = new Composant(10, 10, 5, 5);
		plus.addHandler(new HandlerPlus(credit));

		moins = new Composant(10, 17, 5, 5);
		moins.addHandler(new HandlerMoins(credit));
		
		MouseButtonEvent	ev1 = new MouseButtonEvent(12, 12);
		ev1.press();
		MouseButtonEvent	ev2 = new MouseButtonEvent(17, 16);
		ev2.press();
		MouseButtonEvent	ev3 = new MouseButtonEvent(13, 20);
		ev3.press();
		
		System.out.println("CREDIT="+credit);
		plus.processEvent(ev1);
		System.out.println("EV1+ : "+credit);
		moins.processEvent(ev1);
		System.out.println("EV1- : "+credit);
		plus.processEvent(ev2);
		System.out.println("EV2+ : "+credit);
		moins.processEvent(ev2);
		System.out.println("EV2- : "+credit);
		plus.processEvent(ev3);
		System.out.println("EV3+ : "+credit);
		moins.processEvent(ev3);
		System.out.println("EV3- : "+credit);
		
	}

}
