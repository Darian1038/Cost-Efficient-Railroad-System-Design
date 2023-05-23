This program aims to develop a cost-efficient railroad system for the Knights Train Company. The objective is to create a system that allows students to travel throughout the state of Florida while minimizing expenses. The company requires the railroad system to be cycle-free. The program will use Kruskal's Algorithm to design the cost-efficient railroad system based on the given tracks and their respective costs.

The program takes input from text files, where each file represents a test case. Each line in the text file contains three entries separated by a single whitespace character. The entries are as follows:

VERTEX: Represents a label on a map, which can be a letter or an actual city name.
VERTEX: Represents another label on the map, indicating a destination vertex.
COST: Represents the cost of designing a track between the two vertices.

The program generates an output string that provides information about the cost-efficient railroad system. The output includes the tracks used to build the system, along with the total cost of building the railroad system. Each track is displayed in the format of VERTEX1 --- VERTEX2 $COST, separated by "---". The output is returned as a string.