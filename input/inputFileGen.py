import random
import os

number_of_files = 0
numbers_per_file = 0

try:
    number_of_files = int(input('How many files?: '))
    numbers_per_file = int(input('How many random numbers per file?: '))
except ValueError:
    print("value error")

directory = "./"
files_in_directory = os.listdir("./")
filtered_files = [file for file in files_in_directory if file.endswith(".txt")]
for file in filtered_files:
	path_to_file = os.path.join(directory, file)
	os.remove(path_to_file)

for i in range(number_of_files):

    afile = open("input" + str(i) + ".txt", "w" )

    for j in range(numbers_per_file):
        line = str(random.randint(1, 100000))
        afile.write(line+ "\n")

    afile.close()