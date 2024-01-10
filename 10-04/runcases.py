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
for filename in os.listdir(temp_dir):
    # Check if the file ends with ".in"
    if filename.endswith(".in"):
        # If the file ends with ".in", do nothing (or you can perform some other action)
        pass
    else:
        # If the file does not end with ".in", remove it
        file_path = os.path.join(temp_dir, filename)
        os.remove(file_path)
        # print(f"Removed: {filename}")

# Loop through test cases
for i in range(1, 11):  # Assuming 10 test cases
    input_file = os.path.join(temp_dir, f"0{i}.in")
    if i == 10:
        input_file = os.path.join(temp_dir, "10.in")

    # Run the Java program with input from the input file and a timeout
    try:
        start_time = time.perf_counter()
        process = subprocess.Popen(["java", java_program], stdin=open(input_file, "r"), stdout=subprocess.PIPE, text=True)
        actual_output, _ = process.communicate(timeout=10)  # Set a 10-second timeout
        end_time = time.perf_counter()

        # Print Java output and elapsed time
        elapsed_time_ms = (end_time - start_time) * 1000  # Convert to milliseconds
        print(f"Test case {i} output: {actual_output.strip()}")
        print(f"Elapsed time: {elapsed_time_ms:.2f} ms")

    except subprocess.TimeoutExpired:
        print(f"Test case {i} timed out")

    except Exception as e:
        print(f"Test case {i} encountered an error: {str(e)}")

# Clean up: Remove the temporary directory and its contents
for i in range(1, 11):
    input_file = os.path.join(temp_dir, f"0{i}.in")
    if i == 10:
        input_file = os.path.join(temp_dir, "10.in")
    os.remove(input_file)
os.rmdir(temp_dir)
