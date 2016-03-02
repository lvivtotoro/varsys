# Varsys
Varsys is an experimental programming language and interpreter.  
It is made for fun and is not meant to compete with other languages.  

## Download
Download in the releases page on GitHub.  

## Usage
Varsys files are suffixed with .var  
To interpret these files download the files in on releases page.
Extract the zip file and run _varsys.bat_ if you're using Windows or _varsys.sh_ if you're using Linux or Mac.
Then do as the script says.

## Syntax
Commands in Varsys are single symbols:  
* `>` is to print.
* `@` is the beginning and end of a while statement.
* `!` is to break from a while statement
* `=` is for assigning variables.
* `#` is for telling Varsys that the thing you're dealing with is a number.
* `;` is for ending commands;
* `?` is for starting if statements (from 0.6)
* `/` is for ending if statements (from 0.6)
* `<` is for taking input (from 0.7)


### If Statements (from 0.6)
If statements are quite easy, currently there is only the equals operator to use in an if statement (=):  

    ?something=lol;
    >"something is lol!";
    /
The `?` operator starts an if statement.  
You may compare strings and numbers.  
If the if statement is true then it prints "something is lol!".  
The `/` operator ends the if statement.  

### When to use the Semi-colon
Some operators don't require any semi-colons.  
These operators are the ones that don't require additional data to work such as `!` or `@`  
For these operators to function you must put a new line instead.  

## Automatically Generated Variables
When performing certain commands AGVs may be created.  
There is currently only one:
* whileIndex - Is created when a while loop has started and deletes itself when the loop has ended. whileIndex holds the amount of times a loop was completed.

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
This is like the last program, but with doubles (due to when comparing these values they turn into text 70000 will turn into 70000.0

    >"Muhaha";
    >#999;
This program first prints "Muhaha" then prints 999.0

    @
    >whileIndex;
    ?whileIndex=10000.0;
    !
    /
    @
This program performs a while loop until it was looped 10000 times.  
This program only works from 0.6.
