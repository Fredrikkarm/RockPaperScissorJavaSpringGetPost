package com.example.demo;

import org.springframework.http.MediaType;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RPSController {
	
	private static gameBean game = new gameBean();
	
	@RequestMapping(method = { RequestMethod.POST }, path = "/rps")
	public static String rps(String userInput) {
		
		System.out.println(userInput);
		
		ArrayList<String> compInput = new ArrayList<String>();
		compInput.add("rock");
		compInput.add("paper");
		compInput.add("scissor");
		System.out.println(compInput.get(0));
		
           // generating the index using Math.random()
            int index = (int)(Math.random() * compInput.size());
            String compchoice = compInput.get(index);
            System.out.println("Random Element is :" + compchoice);
        
		
		if(userInput.equals(compchoice)) {
			System.out.println("Tie");
			game.addResult("tie");
			return "tie";
		}else if(userInput.equals("rock") && compchoice.equals(compInput.get(2))) {
			System.out.println("you lose");
			game.addResult("loss");
			return "you lose";
		}else if(userInput.equals("rock") && compchoice.equals(compInput.get(1))) {
			System.out.println("you win");
			game.addResult("win");
			return "you win";
		}else if(userInput.equals("paper") && compchoice.equals(compInput.get(2))) {
			System.out.println("you win");
			game.addResult("win");
			return "you win";
		}else if(userInput.equals("paper") && compchoice.equals(compInput.get(0))) {
			System.out.println("you lose");
			game.addResult("loss");
			return "you lose";
		}
		else if(userInput.equals("scissor") && compchoice.equals(compInput.get(1))) {
			System.out.println("you win");
			game.addResult("win");
			return "you win";
		}
		else if(userInput.equals("scissor") && compchoice.equals(compInput.get(0))) {
			System.out.println("you lose");
			game.addResult("loss");
			return "you lose";
		}else {
			System.out.println("something went wrong");
			return "something went wrong";
		}
		
		
	}
	
	@RequestMapping(value = "/rps", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showMatches() { 
		String result = "";
	
		result += game.toJsonString() + ",";
		
		if (result.length() > 2){
			result = result.substring(0, result.length() - 1);
		}
		
		result = "{ \"Games\":  [" + result + "] }";
		
		return result;
		
	}

}
