# Reversi
Reversi game coded in java with MVC architecture (design pattern), features LAN client-server multiplayer between 2 players with instant chat and gaming vs AI using minimax on several difficulty levels.

- [Architecture](#architecture)
  * [Model](#model)
  * [Controller](#controller)
  * [View](#view)
    + [Launch window](#launch_window)
    + [Game window](#game_window)
    + [Playing vs AI](#playing_vs_ai)
    + [Playing on network](#playing_on_network)
    + [Client game request](#client_game_request)
    + [Incoming game request](#incoming_game_request)    
- [AI](#ai)
- [Network](#network)
  * [Network gaming protocol](#network_gaming_protocol)
  * [Hosting a server](#hosting_a_server)
  * [Joining a server](#joining_a_server)



<br /><br />
<a name="neural-network-illustration"></a>
![neural_network_illustration](neuralnet_layers.png)
<br /><br />

<a name="architecture"></a>
<h1>Architecture</h1>
The architectural pattern chosen for this project was MVC (model-view-controller). This architecture divides the applications concern and code into 3 main elements, the model, view and controller. 

<br /><br />
<a name="mvc"></a>
![mvc](/readme/mvc.png)
<br /><br />

Below is a flowchart of interactions between different elements in this project

<br /><br />
<a name="flow_chart"></a>
![flow_chart](/readme/flow_chart.png)
<br /><br />

<a name="model"></a>
<h2>Model</h2>
Contains the core game logic, ai, client-server code (and more). Contains a very minimal amount of Unit tests to make sure the game logic works. 

<a name="controller"></a>
<h2>Controller</h2>
The controller mainly waits for updates from the view (GUI) to make changes to the game logic, and after the game logic updates, consequently to update the view. As such, the observer pattern was used extensively in the controller code to listen for changes and updates between both model and controller and is responsible for interacting between them.

<a name="view"></a>
<h2>View</h2>
The GUI for this project was developed separately using NetBeans SWING UI. Hence, the need for use of the adapter design pattern, since the interface for the actual GUI code (view) was not available for reference when coding controller and model related code.

<br /><br />
<a name="launch_game_window"></a>
<h3>Launch Window</h3>

![launch_game_window](/readme/launch_game_window.png)
<br /><br />

1.	A game of reversi is played on the same pc, 2 players use the same pc (or a single player against himself).
2.	Play against AI.
3.	Play against another peer on the LAN network.

<br /><br />
<a name="game_window"></a>
<h3>Game Window</h3>

![game_window](/readme/game_window.png)
<br /><br />

1.	Black player points
2.	White player points
3.	Current moving player color
4.	Toggle switch to show possible moves for current moving player

<a name="show_move"></a>
![show_move](/readme/show_move.png)
<br />

5. Sound toggle
6. Game status
7. Reversi board

<a name="reversi_board"></a>
![reversi_board](/readme/reversi_board.png)
<br />

* Outlines in green an empty square that current player may occupy when mouse hovers over it.
* Outlines in blue all of the opponents pieces he will capture if he moves a piece on square outlined in green.
8. Move Me - AI moves for you
9. Restart that game
10. Set AI difficulty
11. Send message to peer
12. Chat box

<br /><br />
<a name="playing_vs_ai"></a>
<h3>Playing Vs AI</h3>

![ai_interface](/readme/ai_interface.png)
<br />

1. First Move - Choose who starts first - human or ai
2. Difficulty level
3. Play - Launch a new game against ai

<br /><br />
<a name="playing_on_network"></a>
<h3>Playing On Network</h3>

![lan_ui](/readme/lan_ui.png)
<br />

1. Hosting peer enables server

<a name="enable_server"></a>
![enable_server](/readme/enable_server.png)
<br />

2.	Hosting peer is supplied with address of server that client peers may connect to.
3.	Client peer inputs server address and requests to join

<a name="client_join"></a>
![client_join](/readme/client_join.png)
<br /><br />

<br /><br />
<a name="client_game_request"></a>
<h3>Client Game Request</h3>

![client_game_request](/readme/client_game_request.png)
<br /><br />

1.	Server address
2.	Connection attempt status
3.	Game request accepted status
4.	Game launching status
5.	Game request status
6.	Cancel game request

<br /><br />
<a name="incoming_game_request"></a>
<h3>Incoming Game Request</h3>

![incoming_game_request](/readme/server_game_request.png)
<br />

1.	Address of connecting client
2.	Accept game request
3.	Decline game request



<a name="ai"></a>
<h1>Artificial Intelligence</h1>
The artificial intelligence used in this project is minimax. The user can choose how many steps ahead the algorithm looks forward by choosing the difficulty level (1-5) accordingly. The reason there is not an option for a level 6 difficulty is because looking 6 moves forward in the algorithm proved to be a lengthy wait, many times more than 15 seconds. 
<br />
The heuristic function simply counts how many points are occupied by the player on the board.



<a name="network"></a>
<h1>Network</h1>
The network architecture used for this project was client-server. Any 2 clients on the same LAN network can play against each other, given one peers knows the other peers ip and port used by the reversi program.
<br />

The project offers 2 main network features:
<br />

1.	Allows 2 peers to play against each other on the same LAN network
2.	Chat with each other after they have started a game using a chat box located at the bottom of the game board.
<br /><br />

![network_ui](/readme/network_ui.png)
<br /><br />

<br /><br />
<a name="network_gaming_protocol"></a>
<h3>Network Gaming Protocol</h3>

1.	Both peers need to have the reverse program installed.
2.	Both peers need to be on the same LAN network
3.	One of the peers opens a server on their pc by checking “Enable connections” which waits for incoming peer requests. An ip and port is supplied, and any client that wishes to request a game must know this address.

<a name="server_enable"></a>
![server_enable](/readme/server_enable.png)
<br />

4.	The server hosting peer must communicate his server address to any other peer that wants to play with him.

<a name="server_address"></a>
![server_address](/readme/server_address.png)
<br />

5.	The client peer supplies the address of the server and clicks “Join”.

<a name="client_join"></a>
![client_join](/readme/client_join.png)
<br />

6.	The client waits for the server peer to accept the game request.

<a name="client_game_request"></a>
![client_game_request](/readme/client_game_request.png)
<br />

7.	The peer hosting the server will receive a game request. He can choose to accept or decline.

<a name="server_game_request"></a>
![server_game_request](/readme/server_game_request.png)
<br /><br />

After game has initiated

*	Both players can send chat messages to each other.
*	Both players choose their move on their turn.
*	In the end of the game they can choose a rematch.
*	If one of the peers disconnects, the other peer is notified.

<br /><br />
<a name="hosting_a_server"></a>
<h3>Hosting a server</h3>

1.	A peer hosts a server that listens for incoming game requests from peer clients
2.	The hosting peer may disable or enable his server at any time.
3.	An incoming game request contains the peer client’s address. The hosting peer may choose to accept or decline the game request.

<br /><br />
<a name="joining_a_server"></a>
<h3>Joining A Server</h3>

1.	Server address validation.

<a name="validation_server"></a>
![validation_server](/readme/validation_server.png)
<br />

2.	Attempt to connect to server

<a name="join_server_attempt"></a>
![join_server_attempt](/readme/join_server_attempt.png)
<br />

3.	After successful connection, wait for hosting peer to accept

<a name="wait_peer_attempt"></a>
![wait_peer_attempt](/readme/wait_peer_attempt.png)
<br />

In case of failure to connect to server

<a name="server_connection_failure"></a>
![server_connection_failure](/readme/server_connection_failure.png)
<br />

4.	Initialize Game

<a name="game_initialize"></a>
![game_initialize](/readme/game_initialize.png)
<br /><br />






