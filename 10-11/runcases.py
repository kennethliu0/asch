import os
import subprocess
import zipfile
import time
# Specify the name of your compiled Java program
java_program = input("Enter Java File Name: ")

# Specify the name of the zip file containing the test cases
zip_file_name = java_program + ".zip"
java_program += ".java"
# Extract the test cases from the zip file to a temporary directory
temp_dir = "temp_test_cases"
with zipfile.ZipFile(zip_file_name, "r") as zip_ref:
    zip_ref.extractall(temp_dir)

# Loop through test cases
for i in range(1, 11):  # Assuming 10 test cases
    input_file = os.path.join(temp_dir, f"0{i}.in")
    if i == 10:
        input_file = os.path.join(temp_dir, "10.in")

    # Run the Java program with input from the input file
    start_time = time.time()
    process = subprocess.Popen(["java", java_program], stdin=open(input_file, "r"), stdout=subprocess.PIPE, text=True)
    end_time = time.time()
    # Get the output of the Java program
    actual_output = process.communicate()[0].strip()

    # Print Java output
    print(actual_output, int((end_time - start_time) * 1000), "ms")

# Clean up: Remove the temporary directory and its contents
for i in range(1, 11):
    input_file = os.path.join(temp_dir, f"0{i}.in")
    if i == 10:
        input_file = os.path.join(temp_dir, "10.in")
    os.remove(input_file)
os.rmdir(temp_dir)