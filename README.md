# Reversi
Reversi game coded in java with MVC architecture (design pattern), features LAN client-server multiplayer between 2 players with instant chat and gaming vs AI using minimax on several difficulty levels.

- [Architecture](#architecture)
- [Network](#network)
- [GUI](#gui)
- [AI](#ai)



  * [Neural Network](#neural-network)
    + [Layers](#neural-network-layers)  
    + [Illustration](#neural-network-illustration)
<br /><br />

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

<a name="model"></a>
<h2>Model</h2>
Contains the core game logic, ai, client-server code (and more). Contains a very minimal amount of Unit tests to make sure the game logic works. 

<a name="controller"></a>
<h2>Controller</h2>
The controller mainly waits for updates from the view (GUI) to make changes to the game logic, and after the game logic updates, consequently to update the view. As such, the observer pattern was used extensively in the controller code to listen for changes and updates between both model and controller and is responsible for interacting between them.

<a name="view"></a>
<h2>View</h2>
The GUI for this project was developed separately using NetBeans SWING UI. Hence, the need for use of the adapter design pattern, since the interface for the actual GUI code (view) was not available for reference when coding controller and model related code.

<a name="view"></a>
<h3>Launch Window</h3>
