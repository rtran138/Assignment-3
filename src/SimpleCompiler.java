//These are all the imports you are allowed, don't add any more!

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to perform numeric operations on a file and output it in command line.
 */
class SimpleCompiler {
    /**
     * This method will parse through a file and put all of the elements into a queue.
     *
     * @param filename name of the file that function will parse through.
     * @return Will return a Queue implemented using Nodes.
     * @throws IOException If the file is unable to be parsed, it will return null.
     */

    public static Node<String> fileToQueue(String filename) throws IOException {
        //given a file name, open that file in a scanner and create a queue of nodes
        //the head of the queue of nodes should be the start of the queue
        //the values in the nodes should be the strings read in each time you call
        //next() on the scanner
        Node<String> head = null;
        Node<String> tail = null;
        File text = new File(filename);
        Scanner scnr = new Scanner(text);

        while (scnr.hasNext()) {
            String s = scnr.next();
            Node<String> newNode = new Node<String>(s);
            if (head == null) {
                head = newNode;
                continue;
            } else if (tail == null) {
                tail = newNode;
                head.setNext(tail);
                continue;
            }
            tail.setNext(newNode);
            tail = tail.getNext();

        }
        return head;
    }

    /**
     * This method will parse through the queue created by fileToQueue and perform all of the given operations.
     *
     * @param input      Queue that is going to be parsed through.
     * @param numSymbols Number of symbols that are going to be processed.
     * @return Returns the elements left in the queue after 1 iteration.
     */

    public Node<String> compile(Node<String> input, int numSymbols) {
        //Given an input queue of symbols, process the number of symbols
        //specified (numSymbols) and update the callStack and symbols
        //variables appropriately to reflect the state of the "SimpleCompiler"
        //(see below the "do not edit" line for these variables).

        //Return the remaining queue items.

        //For example, if input is the head of a linked list 3 -> 2 -> +
        //and numSymbols=2, you would push 3 and push 2, then return the linked
        //list with just the + node remaining.

        Node<String> copy = input;
        int sum;
        try {
            callStack.push(copy.getValue());
            copy = copy.getNext();
        } catch (NullPointerException e) {
            System.out.println("End of Queue");
        }
        String operation = (String) callStack.peek();
        if (operation.equals("+")) {
            callStack.pop();
            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) + symbols.get(x);
            } else if (!symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) + Integer.parseInt(x);
            } else if (symbols.contains(x) && !symbols.contains(y)) {
                sum = Integer.parseInt(y) + symbols.get(x);
            } else {
                int x2 = Integer.parseInt(x);
                int y2 = Integer.parseInt(y);
                sum = y2 + x2;
            }
            callStack.push(Integer.toString(sum));
        }
        if (operation.equals("-")) {
            callStack.pop();
            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) - symbols.get(x);
            } else if (!symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) - Integer.parseInt(x);
            } else if (symbols.contains(x) && !symbols.contains(y)) {
                sum = Integer.parseInt(y) - symbols.get(x);
            } else {
                int x2 = Integer.parseInt(x);
                int y2 = Integer.parseInt(y);
                sum = y2 - x2;
            }
            callStack.push(Integer.toString(sum));
        }
        if (operation.equals("*")) {
            callStack.pop();
            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) * symbols.get(x);
            } else if (!symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) * Integer.parseInt(x);
            } else if (symbols.contains(x) && !symbols.contains(y)) {
                sum = Integer.parseInt(y) * symbols.get(x);
            } else {
                int x2 = Integer.parseInt(x);
                int y2 = Integer.parseInt(y);
                sum = y2 * x2;
            }
            callStack.push(Integer.toString(sum));
        }
        if (operation.equals("/")) {
            callStack.pop();
            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) / symbols.get(x);
            } else if (!symbols.contains(x) && symbols.contains(y)) {
                sum = symbols.get(y) / Integer.parseInt(x);
            } else if (symbols.contains(x) && !symbols.contains(y)) {
                sum = Integer.parseInt(y) / symbols.get(x);
            } else {
                int x2 = Integer.parseInt(x);
                int y2 = Integer.parseInt(y);
                sum = y2 / x2;
            }
            callStack.push(Integer.toString(sum));
        }
        if (operation.equals("print")) {
            callStack.pop();
            System.out.println(callStack.pop());
        }
        if (operation.equals("=")) {
            callStack.pop();
            int value = Integer.parseInt((String) callStack.pop());
            String key = (String) callStack.pop();
            symbols.put(key, value);
        }
        if (operation.equals("+=")) {
            callStack.pop();

            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                symbols.put(y, symbols.get(y) + symbols.get(x));

            } else if (!symbols.contains(x) && symbols.contains(y)) {
                int x2 = Integer.parseInt(x);
                symbols.put(y, symbols.get(y) + x2);
            } else {
                System.out.println("you messed up");
            }
        }
        if (operation.equals("-=")) {
            callStack.pop();

            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                symbols.put(y, symbols.get(y) - symbols.get(x));

            } else if (!symbols.contains(x) && symbols.contains(y)) {
                int x2 = Integer.parseInt(x);
                symbols.put(y, symbols.get(y) - x2);
            } else {
                System.out.println("you messed up");
            }
        }
        if (operation.equals("*=")) {
            callStack.pop();
            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                symbols.put(y, symbols.get(y) * symbols.get(x));

            } else if (!symbols.contains(x) && symbols.contains(y)) {
                int x2 = Integer.parseInt(x);
                symbols.put(y, symbols.get(y) * x2);
            } else {
                System.out.println("you messed up");
            }
        }
        if (operation.equals("/=")) {
            callStack.pop();

            String x = (String) callStack.pop();
            String y = (String) callStack.pop();
            if (symbols.contains(x) && symbols.contains(y)) {
                symbols.put(y, symbols.get(y) / symbols.get(x));

            } else if (!symbols.contains(x) && symbols.contains(y)) {
                int x2 = Integer.parseInt(x);
                symbols.put(y, symbols.get(y) / x2);
            } else {
                System.out.println("you messed up");
            }
        }
        return copy;
    }

    /**
     * Method used to run the code w/o command line arguments.
     */
    public static void testThisCode() {
        //edit this as much as you want, if you use main without any arguments,
        //this is the method that will be run instead of the program
        System.out.println("You need to put test code in testThisCode() to run SimpleCompiler with no parameters.");
    }
    //    //--------------------DON'T EDIT BELOW THIS LINE--------------------
    //    //----------------------EXCEPT TO ADD JAVADOCS----------------------


    /**
     * Integer operations.
     *
     * @variable holds all integer operations.
     */
    public static final String[] INT_OPS = {"+", "-", "*", "/"};
    /**
     * Variable operations.
     *
     * @variable holds all variable operations.
     */
    public static final String[] ASSIGN_OPS = {"=", "+=", "-=", "*=", "/="};

    //or these...

    /**
     * Stack used to perform all operations.
     *
     * @variable holds a callStack.
     */
    public CallStack<Object> callStack = new CallStack<>();

    /**
     * BST used to hold all variables.
     *
     * @variable holds all variables.
     */
    public LookUpBST<String, Integer> symbols = new LookUpBST<>();

    /**
     * This method will run command line args.
     *
     * @param args arguments in the command line.
     */
    public static void main(String[] args) {
        //this is not a testing main method, so don't edit this
        //edit testThisCode() instead!

        if(args.length == 0) {
            testThisCode();
            return;
        }

        if(args.length != 2 || !(args[1].equals("false") || args[1].equals("true"))) {
            System.out.println("Usage: java SimpleCompiler [filename] [true|false]");
            System.exit(0);
        }

        try {
            (new SimpleCompiler()).runCompiler(args[0], args[1].equals("true"));
        }
        catch(IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    //provided, don't change this
    public void runCompiler(String filename, boolean debug) throws IOException {
        Node<String> input = fileToQueue(filename);
        System.out.println("\nProgram: " + Node.listToString(input));

        if(!debug) {
            while(input != null) {
                input = compile(input, 10);
            }
        }
        else {
            Scanner s = new Scanner(System.in);
            for(int i = 1; input != null; i++) {
                System.out.println("\n######### Step " + i + " ###############\n");
                System.out.println("----------Step Output----------");
                input = compile(input, 1);
                System.out.println("----------Lookup BST---------");
                System.out.println(symbols);
                System.out.println("----------Call Stack--------");
                System.out.println(callStack);
                if(input != null) {
                    System.out.println("----------Program Remaining----");
                    System.out.println(Node.listToString(input));
                }
                System.out.println("\nPress Enter to Continue");
                s.nextLine();
            }
        }
    }
}