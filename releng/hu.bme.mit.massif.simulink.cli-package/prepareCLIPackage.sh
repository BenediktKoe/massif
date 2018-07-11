###############################################################################
# Copyright (c) 2010-2017, IncQueryLabs Ltd.
# All rights reserved. This program and the accompanying materials 
# are made available under the terms of the Eclipse Public License v1.0 
# which accompanies this distribution, and is available at 
# http://www.eclipse.org/legal/epl-v10.html 
#
# Contributors: 
#     Peter Lunk - initial API and implementation 
###############################################################################
TARGETDIR=$WORKSPACE/releng/hu.bme.mit.massif.simulink.cli-package
MATLABDIRPARENT=$TARGETDIR
PLUGINSDIR=$WORKSPACE/plugins

mkdir -p $MATLABDIRPARENT/matlab

cp $TARGETDIR/target/hu.bme.mit.massif.simulink.cli-package*-SNAPSHOT.jar $TARGETDIR/matlab/hu.bme.mit.massif.simulink.cli-package.jar

cp $TARGETDIR/Example_MATLAB.slx $MATLABDIRPARENT/matlab
cp $TARGETDIR/Example.simulink $MATLABDIRPARENT/matlab

cp $PLUGINSDIR/hu.bme.mit.massif.simulink.cli/scripts/CLI_CreateMATLABModel_Example.m $MATLABDIRPARENT/matlab
cp $PLUGINSDIR/hu.bme.mit.massif.simulink.cli/scripts/CLI_CreateMassifModel_Example.m $MATLABDIRPARENT/matlab

cd $MATLABDIRPARENT

zip -r hu.bme.mit.massif.simulink.cli-example.zip ./matlab
