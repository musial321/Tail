# Tail
Simulates the tail command for file manipulation in Unix, but completely written in Java.

# How to use
1. Compile file with Javac
2. By default, tail will print the last 10 lines of the input file to standard output. By using the -n command line option, you can instead specify a number of lines to print. The following call shows the first 30 lines of filename: java head -n 30 filename
3. If you want to print using the default options, do the following call: java tail filename
4. Adding the -f command line option will update the file in realtime, so if new data is added to the end of a file, it will then be added.
