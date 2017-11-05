

#include "terminal.h"
#include "status.h"

Terminal terminal(&Serial);
Status status(&terminal);



void homeCommand(){
	Serial.println("Home received");
}

void tareCommand(){
	Serial.println("Tare received");
}
void moveCommand(int dist){
	Serial.print("Move received: ");
	Serial.println(dist);
}


void setup() {
	Serial.begin(115200);

	terminal.ptrHomeCommand = homeCommand;
	terminal.ptrTareCommand = tareCommand;
	terminal.ptrMoveCommand = moveCommand;
}

void loop() {
	unsigned long nowMs = millis();
	terminal.run();
	status.run(nowMs);
//	Serial.print("s:");
//	Serial.print(random(0, 1000));
//	Serial.print(":");
//	Serial.print(random(0, 200));
//	Serial.println();
//	delay(200);
}
