package model;

import model.dataStructures.Edge;
import model.dataStructures.Graph;
import model.dataStructures.Vertice;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Administrator {


    Graph graph;

    public Administrator(){
        graph = new Graph(false);
    }

    public void readFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        sc.nextLine(); //Cleaning first line
        String[] allVertex = sc.nextLine().split(",");
        for (String ver : allVertex){
            System.out.println(ver);
            String[] parts = ver.split(":");
            graph.addVertice(Integer.parseInt(parts[0]), parts[1]);
        }
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String[] edge = parts[2].split(",");

            int from = 0;
            for(int i = 0; i < graph.getVertices().size(); i++){
                String val = ((Vertice) graph.getVertices().get(i)).getValue();
                if(val.equals(parts[0])){
                    from = (int)((Vertice) graph.getVertices().get(i)).getKey();
                }
            }

            int to = 0;

            for(String element : edge){
                System.out.println("1: "+ parts[0] + " 2: "+element);
                for(int i = 0; i < graph.getVertices().size(); i++){
                    int key = 0;
                    String val = ((Vertice) graph.getVertices().get(i)).getValue();
                    if(val.equals(element)){
                        to = (int)((Vertice) graph.getVertices().get(i)).getKey();
                    }
                }
                //System.out.println(Integer.parseInt(parts[1]));
                graph.addEdge(from,to, Integer.parseInt(parts[1]));
            }
            System.out.println(line);
        }

        graph.initialize();
        graph.floydWarshall(graph.getVertices().size());
        System.out.println(graph.prim(Integer.parseInt(allVertex[0].split(":")[0])).getEdges().get(0));
        //graph.printPrim(graph.prim(Integer.parseInt(allVertex[0].split(":")[0])).getEdges().get(0));
        //System.out.println(Arrays.toString(Arrays.asList(graph.getVertices())));
        sc.close();
    }
    public String bestRoute(int initialPoint, int finalPoint){
        return graph.printPath(graph.constructPath(initialPoint, finalPoint));
    }
}
