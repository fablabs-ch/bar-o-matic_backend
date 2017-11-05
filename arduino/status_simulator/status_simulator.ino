

#include "terminal.h"
#include "status.h"

Terminal terminal(&Serial);
Status status(&terminal);



void homeCommand(){
	status.setDistMm(0);
	Serial.println("Home received");
}

void tareCommand(){
	status.setWeightGr(0);
	Serial.println("Tare received");
}
void moveCommand(int dist){
	status.setDistMm(dist);
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
}
