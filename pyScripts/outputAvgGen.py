import os
directory = "./output"

files_in_directory = os.listdir(directory)
filtered_files = [file for file in files_in_directory if file.startswith("part-r-")]

sum = 0
count = 0

for i in filtered_files:
    afile = open(directory + "/" + i)
    keyVal = afile.readline().strip().split()
    sum += int(keyVal[0])
    count += int(keyVal[1])
    afile.close()
    print(i + " (sum,count): " + keyVal[0] + ", " + keyVal[1])

print("")
print("average = ", sum/count)