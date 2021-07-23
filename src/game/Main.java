package game;

import java.util.*;
import static game.InOutUtils.readStringsFromInputStream;
import static game.ProcessUtils.UTF_8;

/**
 * Main samplegame class.
 */
public class Main {

    public static void main(String[] args) {
        List<String> input = readStringsFromInputStream(System.in, UTF_8);
        if(!input.isEmpty()){
            Round round = new Round(input);
            printMovingGroups(makeMove(round));
        }
        System.exit(0);
    }

    private static List<MovingGroup> makeMove(Round round) {
        List<MovingGroup> movingGroups = new ArrayList<>();


        int max = 1;
        int idPlanet = 1;
        int population = 0;
        int sumPopulation = 0;

        List<Planet> myPlanet = round.getOwnPlanets();

        for (Planet pl : myPlanet) {
            sumPopulation += pl.getPopulation();
        }

        for(Planet libertyMinPopylationPlanets : round.getNoMansPlanets())
        {
            if (libertyMinPopylationPlanets.getPopulation() > max && libertyMinPopylationPlanets.getPopulation() < sumPopulation)
            {
                max = libertyMinPopylationPlanets.getPopulation();
                idPlanet = libertyMinPopylationPlanets.getId();
                population = libertyMinPopylationPlanets.getPopulation();
            }
        }
        final int finIdPlanet = idPlanet;
        final int finPopulation = population;

        myPlanet.stream().forEach(planet -> movingGroups.add(new MovingGroup(planet.getId(), finIdPlanet,finPopulation + 1)));
        myPlanet.stream().forEach(planet -> movingGroups.add(new MovingGroup(planet.getId(), finIdPlanet,finPopulation + 1)));

        int min = 300;
        idPlanet = 1;
        population = 0;
        for(Planet noMyMinPopylationPlanets : round.getOwnPlanets())
        {
            if (noMyMinPopylationPlanets.getPopulation() < min)
            {
                min = noMyMinPopylationPlanets.getPopulation();
                idPlanet = noMyMinPopylationPlanets.getId();
                population = noMyMinPopylationPlanets.getPopulation();
            }
        }
        List<Planet> myPlanet_2 = round.getOwnPlanets();
        final int finIdPlanet_2 = idPlanet;
        final int finPopulation_2 = population;

        myPlanet_2.stream().forEach(planet -> movingGroups.add(new MovingGroup(planet.getId(), finIdPlanet_2,finPopulation_2 + 1)));
        myPlanet_2.stream().forEach(planet -> movingGroups.add(new MovingGroup(planet.getId(), finIdPlanet_2,finPopulation_2 + 1)));

        return movingGroups;
    }

    private static void printMovingGroups(List<MovingGroup> moves) {
        System.out.println(moves.size());
        moves.forEach(move -> System.out.println(move.getFrom() + " " + move.getTo() + " " + move.getCount()));
    }

}