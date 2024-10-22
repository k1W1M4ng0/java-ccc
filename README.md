# CodeRunner - a Java framework for the CCC

## Usage

### 1. Clone the project

```bash
git clone https://github.com/k1W1M4ng0/java-ccc.git
cd java-ccc
```

### 2. Add files in `files/`

The file structure should be:

```
files
└── level1
    ├── level1_1.in
    ├── level1_2.in
    ├── level1_3.in
    ├── level1_4.in
    └── level1_5.in
```

### 3. Configure CodeRunner 

The structure can also be configured with static variables.

For the format specifications, `{level}` and `{case}` will be 
replaced with the level and case respectively.

The default values are:
```java
        /****** Configure CodeRunner *******/
        CodeRunner.formatIn = "level{level}_{case}.in";
        CodeRunner.formatOut = "level{level}_{case}.out";
        CodeRunner.formatExampleIn = "level{level}_example.in";
        CodeRunner.formatExampleOut = "level{level}_example2.out";
        CodeRunner.formatLevelFolder = "level{level}";

        CodeRunner.trimOutput = true;        // whether the output should be trimmed before writing to file 
        CodeRunner.printOutput = true;       // whether the file output should be printed
```

### 4. Write a function

Each level should have a function `String levelx(Scanner)`
which reads from the Scanner and returns the output, for example:

```java
    public static String level1(Scanner sc) {
        String line = sc.nextLine();
        return line.toUpperCase() + " ";
    }
```

### 5. Call one of the functions in Main

- `runExample` is for the example test case
- `runCase` is for a single test case
- `runAllCases` is for all test cases

The first argument is the path to the files folder (and should not be changed).  
The second argument is the level.  
The third argument is the test case (runCase) / the amount of test cases (runAllCases).  
The last argument is a reference to the function that should be executed.  

```java
        CodeRunner.runExample("../files", 1, Main::level1);
        CodeRunner.runCase("../files", 1, 1, Main::level1);
        CodeRunner.runAllCases("../files", 1, 3, Main::level1);
```
