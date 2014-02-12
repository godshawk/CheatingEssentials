Cheating Essentials
==================

<code>Taking over CheatingEssentials for Kodewaga</code>


<i>How this mod works:</i>

It works by loading 'modules' that do different things; each module has methods therein that are called when different Forge events are fired. This functionality is in the base Module class, and every module uses this as its superclass. I currently have world rendering, GUI overlay rendering, and ticking, but I'll add more as needs be. Modules automatically override the world render/tick methods, but, generally, only one will be used. There's also a GUI render method in there, but that's not an abstract method, as I added it in kinda late, as well as the fact that many modules will simply never use it. Built against Forge 10.12.0.1024

<i><b>License:</i></b>

Copyright © 2013-2015 godshawk. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

<b><a href="http://www.minecraftforum.net/topic/2388174-">Mod Post on MCF</a></b>

<b><a href="https://www.paypal.com/cgi-bin/webscr?cmd=_flow&SESSION=hQcdGTHc7R79qJptg7BpHVBfasWIdxSd9ReW20rJsqdAkO8IzRvtZEDhbBq&dispatch=5885d80a13c0db1f8e263663d3faee8d7ff5e1e81f2ed97dd1e90bd72966c40c">Donations</a></b>
