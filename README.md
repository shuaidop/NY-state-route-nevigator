# NY-state-route-nevigator

This project not only contains the NY state map but also a map of the University of Rochester and a map of Monroe County, Rochester, NY. Users can use their custom maps to follow the structure of the map file to test the route.
For the project, I use the Dijkstra, disjointed set and Kruskal code form the book Data Structure and Algorithm in Java.

# Input
For the input format, make sure that the command line argument[0] is the name of the input file, such as ur.txt, then enter the -show to see the map which you need to enter in the arg[1], for shortest path, please enter -directions MOREY ANDERSON, or other two names after “-direction” and separated by spaces. When drawing the minimum weight spanning tree, type in -meridianmap.

The example format fot the program arguments is:
```bash
nys.txt -show -dirctions i3 i380
```

# Run Time
the run time for each method is listed below:

Dijkstra: (#edges +#vertex)log(#vertex) 

priority Queue: (#edges)log(#edges)

kruskal: bigger than (#edges)log(#edges) 

# Note

Don’t check the shortest path and MST at the same time which might trigger an error        
