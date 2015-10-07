package module1;

public class AlgorithmControl {
	public static void main(String[] args){
		AlgorithmControl ac = new AlgorithmControl();
		ac.loop();
		ac.decrement();
		ac.increment();
		
		final int steps1 = ac.timer(5000,200);
		System.out.println("Total number of steps: " + steps1);
		
		final int steps2 = ac.timer(5000,50000);
		System.out.println("Total number of steps: " + steps2);
		
		System.out.println("Total number of steps (200): " + steps1);
		System.out.println("Total number of steps (50,000): " + steps2);
		
		//As you increase the number of loops until steps are printed, the total about of steps increases.
		//This is because printing to the console is a very slow operation.
	}
	
	private void loop(){
		for(int i = 1; i <= 16; i++){
			System.out.println(i);
		}
	}
	private void decrement(){
		int i = 10;
		while(i >= -5){
			System.out.println(i);
			i--;
		}
	}
	private void increment(){
		for(int i = 24 ; i <=82; i+=2){
			System.out.println(i/10d);
		}
	}
	private int timer(long maxTime, int loopSteps){
		final long timeStart = System.currentTimeMillis();
		long timeNow = timeStart;
		int steps= 0;
		
		while(timeNow <= timeStart + maxTime){
			steps++;
			timeNow = System.currentTimeMillis();
		
			if(steps % loopSteps == 0){
				System.out.println(steps);
			}
		}
		return steps;
	}
}

