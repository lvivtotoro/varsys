# Varsys
Varsys is an experimental programming language and interpreter.  
It is made for fun and is not meant to compete with other languages.  

## Download
Download in the releases page on GitHub.  

## Usage
Varsys files are suffixed with .var  
To interpret these files open up the Terminal or the Command Prompt.  
Type in:  
`java -jar varsys.jar (file location)`
If you're on Linux or Mac put `./` before `varsys.jar`.  

## Syntax
Commands in Varsys are single symbols:  
* `>` is to print.
* `@` is the beginning and end of a while statement.
* `!` is to break from a while statement
* `=` is for assigning variables.
* `#` is for telling Varsys that the thing you're dealing with is a number.
* ';' is for ending commands;

## Examples
    @
    !
    @
This program _would_ be stuck in a while loop until the computer runs out of memory.  
The `!` in the code breaks the while statement after the while loop was started.

    =var "value";
    >var;
This program sets variable "value" to the value var then prints it.

    =#var 70000;
    >var;
This is like the last program, but with integers.

    >"Muhaha";
    >#999;
This program first prints "Muhaha" then prints 999.
