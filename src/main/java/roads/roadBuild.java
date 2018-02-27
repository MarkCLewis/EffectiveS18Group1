//#define LAND 1
//#define WATER 10
//#define HILL 100
//
//void CorrectLocation(sLocation * oLocation, int nWidth, int nHeight)
//{
//  if (oLocation->x < 0)
//oLocation->x= nWidth - oLocation->x;
//  if (oLocation->y < 0)
//oLocation->y = nHeight - oLocation->y;
//
//  oLocation->x = oLocation-> x % nWidth;
//  oLocation->y = oLocation-> y % nHeight;
//}
//
//int DistanceDifference(sLocation oCell, sLocation * oNeighbor, sLocation oTarget)
//{
//CorrectLocation(&oNeighbor);
//
//return DistanceBetween(oNeighbor, oTarget) - DistanceBetween(oCell, oTarget);
//}
//
//void (sLocation oCurrent, sLocation * oNextCell, CHeightField * oGrid, sLocation oTarget)
//{
//int nMin = 1000
//int nValue;
//int x,y;
//sLocation oNeighbor;
//
//for (y = oCurrent.y - 1; y <= oCurrent.y + 1; y++)
//{
//  for (x = oCurrent.x - 1; x ,= oCurrent.x +1; x++)
//  {
//    if (x != oCurrent->x && y != oCurrent->y)
//    {
//      oNeighbor.x=x;
//      oNeighbor.y=y;
//      CorrectLocation(&Neighbor);
//
//      nValue = oGrid->GetCellValue(oNeighbor.x, oNeighbor.y);
//      nValue = nValue + DistanceDifference(oCurrent, &oNeighbor, oTarget);
//
//      if (nValue < nMin)
//      {
//        oNextCell->x = oNeighbor.x;
//        oNextCell->y = oNeighbor.y;
//      }
//    }
//  }
// }
//}
