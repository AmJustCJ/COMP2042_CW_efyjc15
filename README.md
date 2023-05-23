<li>COMP2042 Software Maintenance Coursework</li>
<li>Name: Chang Jiun Wen</li>
<li>Student IDL 20307145</li>
<br>

<br><br>
<li>How to compile the code to produce the application?</li>
-Download ZIP folder from GitHub, unzip and open in Intellij. Or unzip ChangJiunWen_Intellij_19.zip, open COMP2042_CW_efyjc15-main in Intellij with Open Project
<br><br>
<li>Where Javadoc documentation is stored</li>
-in javadoc folder within COMP2024ChangJiunWen
-edit: the full javadoc is uploaded on github, not on the zip file submitted, sorry sir
<br><br>
<li>List of features implemented and are working properly</li>
<ul>
<li>Fix score bug</li>
<br>
<li>Fix arrow key bug (random key will spawn number)</li>
<br>
<li>re-scale GUI</li>
<br>
<li>Fix quit button, will close the entire GUI once quit button is pressed</li>
<br>
<li>fix text size</li>
<br>
<li>create a start page with play button</li>
<br>
<p>-List of changes in main page</p>
<ul>
<li>Upon clicking play button, the starting main page will close and the game will show up</li>
<br>
<li>Force user to enter username without spacebar and force them to choose a game mode from the choice box before playing the game</li>
<br>
<li>In the main page, other than Play button and choice box for game mode. There's also Select Background, LeaderBoard, and How to play button</li>
<br>
<li>Clicking Select Background button will allow user to choose which background for the main page they want</li>
<br>
<li>Clicking LeaderBoard button will allow user to see the leaderboard score of every player in every game mode</li>
<br>
<li>Clicking How to play button will display information on how to play the game</li>
<br>
<li>Added music when the application runs</li>
<br>
</ul>
<p>-List of changes in the game</p>
<ul>
<li>Added 2 new game mode, TwoThree mode and Drunk mode</li>
<br>
<li>In TwoThree mode, instead of spawing "4", it will spawn "3" making it harder for user to win. However the user can win the the tile reach "2048" or "1536"</li>
<br>
<li>In Drunk mode, the controls are reverse. For example, pressing up arrow key will move the tiles down. This goes for all 4 arrow keys</li>
<br>
<li>Fix the bug where for example, a straight tile of 4,4,4,4 will become 0,4,4,8. My version of this application will make it into 0,0,8,8</li>
<br>
<li>Once the player lose, a sound effect will play and user can either quit the application completely or go back to main. <br>
Either way, the username and score of player will be record inside text file of each game mode</li>
<br>
<li>When the player win, a sound effect will play and a pop up win scene will appear asking the user whether they want to quit to main or not.<br>
If user quit to main, the username and score will be store and go back to main.<br>
Otherwise, user can continue to play the game until no moves available, then their username and score will be store</li>
<br>
</ul>
<li>player username and score will store in text file of respected game mode. 
it will then be store in an arraylist and sorted. And finally display in leaderBoard.</li>
<li>Added movement class that separate movement methods and cell generating methods from gameScene</li>
</ul>
<br><br>
<li>List of features implemented and are not working properly</li>
-none
<br><br>
<li>List of features Not implemented</li>
-Select background will also change the background image of the game. 
Unfortunately due to limited time, this was not implemented
<br><br>
<li>List of new Java classes introduced</li>
<ul>
<li>Account.java</li>
<li>AccountList.java</li>
<li>LeaderBoard.java</li>
<li>InfoControl.java</li>
<li>SelectBG.java</li>
<li>Movement.java</li>
</ul>
<br><br>
<li>List of Java classes that I modified</li>
<ul>
<li>Cell.java</li>
<li>Controller.java</li>
<li>EndGame.java</li>
<li>GameScene.java</li>
<li>Main.java</li>
<li>TextMaker.java</li>
</ul>
<br><br>
