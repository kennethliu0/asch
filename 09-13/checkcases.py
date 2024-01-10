import os
import subprocess

# Specify the name of your compiled Java program
java_program = input("Enter Java File Name: ")

# Loop through test cases
for i in range(1, 11):  # Assuming 10 test cases
    input_file = f"0{i}.in"
    expected_output_file = f"0{i}.out"
    if i == 10:
        input_file = "10.in"
        expected_output_file = "10.out"


    # Run the Java program with input from the input file
    process = subprocess.Popen(["java", java_program], stdin=open(input_file, "r"), stdout=subprocess.PIPE, text=True)

    # Get the output of the Java program
    actual_output = process.communicate()[0]

    # Read the expected output from the expected output file
    with open(expected_output_file, "r") as f:
        expected_output = f.read()

    # Compare actual and expected output
    if actual_output == expected_output:
        print(f"Test case {i}: Passed")
    else:
        print(f"Test case {i}: Failed")