# Fitness Workout Log Analyzer

Java 17 app that reads workout CSV data and analyzes training stats. </br>
Everything is done using Stream API for practice.

## What it does
- Parses `data.csv` into workout sessions
- Calculates total volume per exercise (sets × reps × weight)
- Finds best workout day by volume
- Tracks personal records (heaviest sets)
- Shows exercise frequency (days trained)

## How to run

- Run `CsvWriter.java` : generates the csv data file.
- Run `Main.java` : filtered results.

## Tests

- Run `LogParserTest.java` : run the tests.
