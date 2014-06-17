package rep.services;

public class StatisService { 
	public void work() {
		new Thread(new StatisUtil()).start();
	} 
}
