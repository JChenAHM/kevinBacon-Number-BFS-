kevinBacon-Number
=================
In this repo we will investigate the six degrees of Kevin Bacon. Two actors or actresses are linked if they appeared in a movie together. The Kevin Bacon number of an actor is the shortest chain of links that leads to Kevin Bacon. For example, Robert De Niro has a Kevin Bacon number of 1 because he appeared in Sleepers with Kevin Bacon. Elvis Presley's number is 2: although Elvis did not appear in any movie with Kevin Bacon, he was in Change of Habit with Edward Asner, and Asner appeared in JFK with Kevin Bacon. We are going to read in a file containing a list of movies and the actors that appeared in them and compute the Kevin Bacon numbers for specific two actors.

We are provided the data sets from Internet Movie Database. In the input file, each line in the data file consists of a movie title, followed by a list of actors and actresses that appeared in that movie, delimited by the character '/'. Here is an abbreviated example:

Picture Perfect (1997)/Aniston, Jennifer/Bacon, Kevin/Dukakis, Olympia/Mohr, Jay

Planes, Trains & Automobiles (1987)/Bacon, Kevin/Candy, John/Martin, Steve/Robins, Laila

Beach, The (2000)/DiCaprio, Leonardo/York, Daniel/Patarakijjanon, Patcharawan
