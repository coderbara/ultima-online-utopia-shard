sub camping()
   UO.DeleteJournal()
   repeat
      uo.usefromground('0x0DE1')
      wait(1100)
   until UO.InJournal('No item found.') OR UO.SkillVal('Camping') < 1000
end sub