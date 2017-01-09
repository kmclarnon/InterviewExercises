# Exercie 1
In this exerciew we will be dealing with phone numbers.  Specifically the input to your program will be a string in the format  

```
[file path] - [operation]
```
Where the operation is a number corresponding to the operation to perform:

 1. Count the occurences of area codes in the input
 2. TBD
 3. TBD
 
Your program must parse the file given in the input file path to determine the required information and write the results to Standard Out (via `System.out.println(...)` or equivalent).  The file will contain phone numbers, one per line

**Note:** For the purposes of this exercise, a phone number is considered valid if it is in the following format
```
(000) 000-0000
```

### Operation 1 - Count the occurences of Area Codes

Example program input: 

```
/path-to-file/input.txt - 1
```

Example input.txt contents:

```
(419) 386-4309
(883) 537-6238
(330) 193-7088
(320) 987-1469
(534) 239-5526
(826) 506-7798
```

Your task is to find how many times each Area code occurs in the input file. Once you have this information, you must write the results in the format
```
[Area Code]:[# of occurences]
```
 to standard output sorted first in descending order by number of occurences and second in descending order by area code, for example:
```
603:7
602:7
867:5
401:4
```
