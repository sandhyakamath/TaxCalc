<p>The TaxCalculator app computes taxes for various archetypes, including ordinary citizens, senior citizens, and senior citizen females. It is structured into three layers: the UI Layer, the Middle Layer, and the Engine.
</p>
<ul>
  <li>UI Layer: This layer captures demographic and income details. The DataViewHandler interacts with the facade API.</li>
  <li>Middle Layer: This consists of the facade layer or API, command dispatcher, and plugin manager. Data Transfer Objects (DTOs) are used to transfer data between layers. The data is passed to the computation engine via the command dispatcher.</li>
  <li>Engine: This is where the tax computation takes place.</li>
</ul>
<p>
  The system is designed to be extensible through a plugin system. The command dispatcher, with the assistance of the plugin manager, extracts the tax computation logic, including the plugin type, archetype, and command, from an XML file. The computation engine then executes different logic based on the received command. It uses Java classes, Python scripts, SLANG scripts, or C++ classes via JNI to calculate the tax. The computed result is subsequently transferred to the UI layer for display.
</p>
