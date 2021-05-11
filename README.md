# Tic tac toe app
</br>
This is a game where you can play against others online or against a bot.


</br></br>
## Description
</br>
This app is a school project in the subject IKT205 - application development at the University of Agder, Grimstad. It is written in Kotlin, Android Studio. The app uses an API in JSON format for communicating with a server which is hosted from Heroku. The API accepts an object that has three properties: players (array), gameId (string) and state (2D array). Communication has to be validated with a header parameter in the requests with a valid key. Server and API configurations is not part of the project and this app only contains code for the game.


</br></br>
## App content and features
</br>
The app has a GameService class that performs four calls to the API on the server. All calls are JsonObjectRequests which are added to a request queue. Polling is done every three seconds. If any of the calls result in HTTP error codes, the user will get a toast with appropriate message. 

</br></br>
### Main menu
</br>
The user can create a new game, join an existing game or choose to play against a bot. When create game or join game are clicked an alert dialog is displayed as a fragment. For both cases the user is prompted to add a player name, but when joining, game id is also required. If some input fields are left empty, a toast is displayed with information of what is missing and that it is required.

</br></br>

<img src=".\screenshots\main_menu.jpg" alt="main_menu"  width="30%" height="30%" /> 


</br></br>
### Gameplay online
</br>
When a game is created GameActivity displays a message that is waiting for player two to join. The gameboard is deactivated until player two has joined. A toast is used to inform why it is not possible to start the game yet. The player that starts the game always has mark "X". Same is applied when playing against bot. Player two's name is displayed when the player has joined the game. When joining a game player two is shown a message that it is waiting for player one's first move and the gameboard is disabled until the state in the poll request has changed. To prevent cheating it is not possible to set a mark two times in a row. The app returns a toast that tells the player that it is not your turn.

</br></br>
<img src=".\screenshots\game_01_created.jpg" alt="game_created"  width="30%" height="30%" /> <img src=".\screenshots\game_03_gameplay.jpg" alt="gameplay" width="30%" height="30%" />


</br>
After a cell on the game board is marked, it is deactivated and it is checked whether the player has won. When receiving a poll, it is checked whether the opponent has won. During gameplay a message is displayed that it is waiting for the other players move until the state in the poll request has changed. When one of the players has won, a message is displayed stating that you have won or lost and in the event of a draw, this is displayed and the polling is stopped. After the game is finished, a button is displayed where you can start a new game with the same game id which also starts the polling again.
</br></br>
If one of the players click the return button to the main menu, an alert appears which asks for confirmation for leaving the game. If one of the players returns to the main menu during gameplay, the other player will be notified that the other has left the game and that they have won. Since the server has predefined objects it accepts, I solved this by sending an update to the sever with a specific state which only contains strings of 3's. It is then checked in the poll function if received state equals this.

</br></br>
<img src=".\screenshots\game_02_you_lose.jpg" alt="game_lose"  width="30%" height="30%" /> <img src=".\screenshots\game_04_draw.jpg" alt="game_draw" width="30%" height="30%" /> <img src=".\screenshots\game_05_opponent_left_game.jpg" alt="opponent_left" width="30%" height="30%" />


</br>
Testing of the apk for the app revealed that randomly selected cells sometimes appeared on the game board when creating a new game or joining a game the second time. After a lot of debugging, I could still not find the reason why this occurs, but I assume that it is something that "hangs" after the previous game. A temporary solution to prevent this from happening was to reset all the variables used in the GameManager object in GameActivity's onDestroy() function. 


</br></br>
### Gameplay vs bot
</br>
You can choose whether you want to play as "X" or "O". The bot that is implemented does not take into account that it will block your chances of winning or try to win itself. It randomly sets marks in the available cells. This can be improved in the future to make it smarter and set it's marks based on where you choose to put your marks. The status display when the game is finished is implemented in the same way as when playing online. The button to start a new game appears and you can again choose which mark you want to play with.

</br></br>
<img src=".\screenshots\bot_01.jpg" alt="bot_01"  width="30%" height="30%" /> <img src=".\screenshots\bot_02.jpg" alt="bot_02"  width="30%" height="30%" />


</br></br>
### Additional information
</br>
* The classes MainActivity, GameActivity and GameBotActivity are created as activities.
* GameManager, GameService and APIEndPoints are created as objects to make them as a singleton.
* Data class Game is parcelable and contains only players, gameId and state.
* All strings used in the code except the log messages are sorted into different xml files according to the context to which they belong.
* ExampleInstrumentedTest contains tests for the four requests to the server.
* The creator of game server and API is our lecturer Christian Robere Simonsen, professor at UiA.

</br></br>
<img src=".\screenshots\app_icon.jpg" alt="app_icon"  width="30%" height="30%" /> <img src=".\screenshots\app_images.jpg" alt="app_images"  width="30%" height="30%" /> 

</br>
The app icon and the X and O images are taken from www.flaticon.com/free-icons/





