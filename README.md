# Automated Gradebook Aggregator

This Java application, "Automated Gradebook Aggregator," is designed to automate the process of combining multiple CSV-based gradebooks into a single master gradebook and a summary gradebook. This tool is particularly helpful for professors who need to aggregate grades from various sections into a centralized file.

## Table of Contents

- [Introduction](#automated-gradebook-aggregator)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **CSV File Format**: The program handles CSV files with a specific format, including headers, maximum grades, student IDs, names, and grade details.
- **Merging**: It merges individual section grade files into a single master gradebook.
- **Summary Generation**: The application generates a summary gradebook containing overall grades and a points breakdown for each grading category.
- **Details File**: It creates a details file that includes a detailed breakdown of every student's marks in the individual CSV files.
- **Data Structures**: The program uses data structures like hash tables and array lists to efficiently process and store the data.
- **Object-Oriented Design**: The code follows good Object-Oriented Programming (OOP) practices by using classes and encapsulation.
- **Documentation**: The code is well-documented, following programming assignment guidelines.

## Prerequisites

Before using this application, make sure you have the following:

- Java Development Kit (JDK) installed on your system.
- CSV files to be merged, following the specified format.
- Basic knowledge of using the command line.

## Usage

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/automated-gradebook-aggregator.git


Run the program with the score files in the project folder 'exams_1, exams_2, homework_1, homework_2, quizzes_1, quizzes_2.'

The program will create two output files:
output-details.csv: The details file with a breakdown of student marks.
output-summary.csv: The summary file with overall grades and category points breakdown.
## Project Structure
The project structure is as follows:

PA2Main.java: The main Java application that handles reading, merging, and generating output files.
Student.java: A class representing student data with getter and setter methods.
README.md: This documentation file.
## Contributing
If you'd like to contribute to this project, please fork the repository, make your changes, and submit a pull request. You can also open issues for bug reports or feature requests.

## License
This project is a Washington State University Cpt_S 233 project.
