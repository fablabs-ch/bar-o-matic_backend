void setup() {
	Serial.begin(115200);
}

void loop() {
	Serial.print("s:");
	Serial.print(random(0, 1000));
	Serial.print(":");
	Serial.print(random(0, 200));
	Serial.println();
	delay(200);
}
