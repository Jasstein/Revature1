/*1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.*/

function fib(num) {
	if (num <= 1) 
		return 1;
	return fib(num - 1) + fib(num - 2);
}

console.log("1. Fifth fibonacci number is:", fib(5));

/*2. Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array.
Return the sorted array.*/

function bubbleSort(numArray){
    var swapped = true;
    while (swapped)
    {
        swapped = false;
        for (var i = 0; i < numArray.length - 1; i++)
        {
            if (numArray[i] > numArray[i + 1])
            {
                var tmp = numArray[i + 1];
                numArray[i + 1] = numArray[i];
                numArray[i] = tmp;
                swapped = true;
            }
        }
    }

    return numArray;
}


var array = [12, 98, 54, 173, 8, 41];
bubbleSort(array);
console.log("2. Bubble sort array [12, 98, 54, 173, 8, 41]:", array);

/*3. Reverse String
Define function: reverseStr(someStr)
Reverse and return the String.*/

function reverseStr(someStr) {
	var rev = "";
	for (var i = 0; i < someStr.length; i++){
		rev = someStr[i] + rev;
	}
	return rev;
}

console.log("3. Hello reversed is:", reverseStr("Hello"));

/*4. Factorial
Define function: factorial(someNum)
Use recursion to compute and return the factorial of someNum.*/

function factorial(someNum) {
	if(someNum<=1)
		return 1;
	return someNum * factorial(someNum - 1);
}

console.log("4. 5 factorial is:", factorial(5));

/*5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.*/
function substring(someStr, length, offset) {
	var sub = "";

	if (offset > someStr.length || offset + length > someStr.length)
    {
        alert("Cannot get substring from input string.");
    }

	for(var i = offset; i < length + offset; i++){
		sub = sub + someStr[i];
	}
	return sub;
}

console.log("5. America, length 3, offset 2:", substring("America", 3, 2));

function isEven(someNum) {
	while(someNum >= 2)
		someNum-=2;
	return (someNum==0);
}

console.log("6. 6 is even:", isEven(6));
console.log("6. 7 is even:", isEven(7));

/*7. Palindrome
Define function isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false*/

function isPalindrome(someStr){
	for(var i = 0; i < someStr.length/2 - 1; i++){
		if(someStr[i] != someStr[someStr.length-i - 1])
			return "false";
	}
	return true;
}

console.log("7. huuh is palindrome:", isPalindrome("huuh"));
console.log("7. back is palindrome:", isPalindrome("back"));

/*8. Shapes
Define function: printShape(shape, height, character)
shape is a String and is either "Square", "Triangle", "Diamond".
height is a Number and is the height of the shape. Assume the number is odd.
character is a String that represents the contents of the shape. Assume this String contains just one character.
Use a switch statement to determine which shape was passed in.
Use the console.log function to print the desired shape.
Example for printShape("Square", 3, "%");
%%%
%%%
%%%
Example for printShape("Triangle", 3, "$");
$
$$
$$$
Example for printShape("Diamond", 5, "*");
  *
 ***
*****
 ***
  **/

function printShape(shape, height, character){
	var result = "";
	switch(shape){
		case "Square":
		for(var i = 0; i < height; i++){
			for(var j = 0; j < height; j++){
				result = result + character;
			}
			result = result + "\n";
		} break;
		case "Triangle":
		var x = 1;
		for(var i = 0; i < height; i++){
			for(var j = 0; j < x; j++){
				result = result + character;
			}
			result = result + "\n";
			x++;
		} break;
		case "Diamond":
		for (var j = 1; j <= height; j++){
			for(var i = 0; i < height - j; i++){
				result = result + " ";
			}
			for(var i = 0; i < j; i++){
				result = result + " " + character;
			}
			result = result + "\n";
		}
		for (var j = height - 1; j >=1; j--){
			for(var i = 0; i < height - j; i++){
				result = result + " ";
			}
			for(var i = 0; i < j; i++){
				result = result + " " + character;
			}
			result = result + "\n";
		}
		break;
	}
	return result;
}

console.log("8. Square 3 *:", printShape("Square", 3, "*"));
console.log("8. Triangle 3 #:", printShape("Triangle", 3, "#"));
console.log("8. Diamond 5 $:", printShape("Diamond", 5, "$"));

/*9. Object literal
Define function traverseObject(someObj)
Print every property and it's value.*/

var soldier = {name:"Bob", weapon:"pitchfork", rank:"grunt"};

function traverseObject(someObj){
	var x = someObj.name;
	var y = someObj.weapon;
	var z = someObj.rank;
	var sold = [x, y, z];
	return sold;
}

console.log("9. Soldier properties are: ", traverseObject(soldier));

/*10. Delete Element
Define function deleteElement(someArr)
Print length
Delete the third element in the array.
Print length
The lengths should be the same.*/

var toBeDel = [1, 2, 3, 4];

function deleteElement(someArr){
	console.log(someArr.length);
	delete someArr[3];
	console.log(someArr.length);
 }
console.log("9.")
deleteElement(toBeDel);

/*11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.*/

var toBeSpl = ["All", "Hail", "The", "King"];

function spliceElement(someArr){
	console.log(someArr.length);
	someArr.splice(3,1);
	console.log(someArr.length)
}
console.log("10.")
spliceElement(toBeSpl);

/*12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
	var john = new Person("John", 30);*/

function Person(name, age){
	this.name = name;
	this.age = age;
}

var john = new Person("John", 30);

/*13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
	var john = getPerson("John", 30);*/

function getPerson(name, age){
	return {"name": name, "age": age};
}

var john2 = getPerson("John", 30);
console.log(john2);