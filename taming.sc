# Тамит животное, пока не затамит.
sub taming()
   UO.charprint(UO.GetSerial(), '172','Кого затамить?')
   uo.exec('addobject Animal')
   while uo.targeting() 
      wait(100) 
   wend
   
   Repeat
      UO.DeleteJournal()
      UO.UseSkill('Animal Taming', 'last')
      wait(10000) 
      Repeat
         wait(500)
         if UO.InJournal("far away|can't tame|not within your line|Targetting cancelled|creature looks") then
            return 
         endif
      until UO.InJournal('You failed to tame|accept you as master|You must wait')
      wait(500)
   until UO.InJournal('accept you as master|creature looks')
end sub