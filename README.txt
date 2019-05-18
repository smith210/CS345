Elana Cueto, Martin Smith
Assigment 2 - Final Milestone
README.txt


How to compile our code!:

	Compile and javac all files! Within the terminal, enter the following.

	javac CastingOffice.java Deadwood.java Board.java ExtraRole.java GameTiles.java invalidPlayerException.java Location.java Main.java MainRole.java Player.java Roll.java Scene.java SceneDeck.java Set.java userInput.java Wallet.java Work.java

When executing the program, you must enter the following:

	java Main <numberPlayers> 
	// 2 or 3, must be type int

	Please give numbers either 2 or 3, for players, any other number or input should throw an error.

Instructions for running the code:

	The code will prompt the user to enter their move, as follows:

			>>>Enter your move:
	
	You can enter the following prompts:
		move 		-> if player hasn't moved, the system will display the movable spaces as follows (for example):
						 	>>> Enter your move: move
								0: Secret Hideout
								1: Casting Office
								2: Bank
								3: General Store
							>>> Where do you want to move to? (if you don't want to move, type "no"): 2     <---(inputted the ID associated)
								Move successful! You have moved from the Ranch to the Bank.
						
						Note that you must input the ID associated with the location you want to move to, otherwise it will print an error statment
						and remprompt the question. Also if you don't want to move, type "no", and it will return to prompting for a move. 
		
		upgrade 	-> if a player is not maxed out on their acting level and resides on the Casting Office location (in this case, it identifies
						Casting Office through its ID), the Casting Office will welcome the user and prompt them as follows:
			
							>>>	Enter your move: upgrade

								Welcome to the Casting Office!
								Here are the Prices:
								Level 2: 4 Dollars OR 5 Credits
								Level 3: 10 Dollars OR 10 Credits
								Level 4: 18 Dollars OR 15 Credits
								Level 5: 28 Dollars OR 20 Credits
								Level 6: 40 Dollars OR 25 Credits

								You currently have: 13 Dollars, 10 Credits.  <--- displays the current player's currency 
							>>>	What level do you want to level up to? (if you don't want to upgrade, type "no"): 2  <--- input desired level 
							>>>	Do you want to pay in Dollars or Credits: Dollars <----- this statement appears only when user can pay with both
								Paying in dollars...
								You paid 4 Dollar
		
						If the user can pay with ONLY credits or ONLY dollars, the program will automatically take the required amount
						out of the currency that fullfills the payment. If the user can pay with both (as above), the program will prompt 
						user which currency to pay with, and will pay with the specified currency, and then the program will level up the user.
						If the user can't pay for the level they want, the program will prompt the user stating that, and return to prompt for a
						command. The user will not level up to their desired level.
							
		work		-> If a player is on a Location that has not finished shooting, there are two routes the program can take the user.
						The first is if the current Player is not working and they decide to work. The program will display the available
						acting positions that the user can work on, such as follows:

							>>>	Enter your move: work
								Scene being filmed: Go West, You!, Scene no. 30
								Budget of the film: $3 million
								Available Jobs within your Acting Level: 
								0: EXTRA ACTOR: Crusty Prospector, Actor Level 1
								1: EXTRA ACTOR: Dragged by Train, Actor Level 1
								2: EXTRA ACTOR: Preacher with Bag, Actor Level 2
							>>>	Which Role do You Want? (if you don't want to work, type "no"):  <---prompt for user, must put corresponding ID

						If the player has moved prior to calling this command, this is all that the user can do work-wise. If they have not moved
						prior, then the program will ask to rehearse or act, as follows:
				
								You have completed 0 rehearsals. <-- will implement number of rehearsals Player has taken for the job
							>>>	What do you want to work on:

						There are two options for this input, which are only the following:
								1.) rehearse   ->   will increment the number of rehearsals
								2.) act		   ->   based on the product of roll and rehearsals, will determine how much currency player makes
						An example of act is displayed below: 
							>>>	What do you want to work on: act
								You rolled a 6. With your rehearsals, that raised your roll to a 6 <--- with this example, there are 0 rehearsals.
								You acted succesfully!
								You recieved payment of 1 Dollar
								You recieved payment of 1 Credit
								There is 2 shots left on Train Station.   <---decrements, and displays number of scenes left
						
						Note that rehearsing and acting can only happen once after calling work. 

		end			->	end the current player's turn
		amount		->	display the currency of the current player
		who			->	display the current player's information stats
		where		->	display where current and other players are located, and if they're working
		rename		->	reset the player's name based from user input

	
